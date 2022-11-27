package com.samplebank.accountservice.service;

import com.samplebank.accountservice.client.TransactionManagementClient;
import com.samplebank.accountservice.exception.CustomerNotFoundException;
import com.samplebank.accountservice.model.Account;
import com.samplebank.accountservice.model.AccountInfo;
import com.samplebank.accountservice.model.CustomerAccountsInfo;
import com.samplebank.accountservice.model.Customer;
import com.samplebank.accountservice.mapper.repository.AccountRepository;
import com.samplebank.accountservice.mapper.repository.CustomerRepository;
import com.samplebank.transactionmanagement.dto.TransactionRequestDTO;
import com.samplebank.transactionmanagement.dto.TransactionRequestDTO.OperationTypeEnum;
import com.samplebank.transactionmanagement.dto.TransactionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final TransactionManagementClient transactionManagementClient;

    @Transactional
    public Account createAccount(UUID customerId, BigDecimal initialAmount) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new CustomerNotFoundException(customerId);
        }

        Account account = Account.builder().customer(customer).build();
        account = accountRepository.save(account);

        if (!initialAmount.equals(BigDecimal.ZERO)) {
            TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO()
                    .accountId(account.getId())
                    .operationType(initialAmount.compareTo(BigDecimal.ZERO) > 0 ? OperationTypeEnum.TOP_UP : OperationTypeEnum.WITHDRAWAL)
                    .amount(initialAmount.abs());
            transactionManagementClient.create(transactionRequestDTO);
        }

        return account;
    }


    public CustomerAccountsInfo getAccountsInfoByCustomerId(UUID customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new CustomerNotFoundException(customerId);
        }

        CustomerAccountsInfo customerAccountsInfo = new CustomerAccountsInfo();
        customerAccountsInfo.setName(customer.getName());
        customerAccountsInfo.setSurname(customer.getSurname());
        customerAccountsInfo.setAccountsInfo(new ArrayList<>());

        List<Account> accounts = accountRepository.findByCustomerId(customerId);
        for (Account account: accounts) {
            List<TransactionResponseDTO> transactions = transactionManagementClient.getByAccountId(account.getId()).getBody();

            customerAccountsInfo.getAccountsInfo().add(AccountInfo.builder()
                .id(account.getId())
                .transactions(transactions)
                .balance(transactions.stream()
                        .map(transaction -> transaction.getOperationType() == TransactionResponseDTO.OperationTypeEnum.TOP_UP
                                ? transaction.getAmount()
                                : transaction.getAmount().negate())
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .build());
        }

        return customerAccountsInfo;
    }
}
