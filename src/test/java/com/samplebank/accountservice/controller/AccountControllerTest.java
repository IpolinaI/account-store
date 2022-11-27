package com.samplebank.accountservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samplebank.accountservice.client.TransactionManagementClient;
import com.samplebank.accountservice.mapper.repository.AccountRepository;
import com.samplebank.accountservice.mapper.repository.CustomerRepository;
import com.samplebank.accountservice.model.Account;
import com.samplebank.accountservice.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @MockBean
    private TransactionManagementClient transactionManagementClient;

    @Test
    void createWhenRequestIsValidReturnsExpectedResult() throws Exception {
        Customer customer = customerRepository.save(Customer.builder().name("name").surname("surname").build());

        mockMvc.perform(post("/account-store/api/accounts/{customerId}", customer.getId().toString())
                        .queryParam("initialAmount", BigDecimal.TEN.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.customerId").value(customer.getId().toString()));
    }

    @Test
    void createWhenCustomerDoesNotExistReturnsNotFoundStatus() throws Exception {
        mockMvc.perform(post("/account-store/api/accounts/{customerId}", UUID.randomUUID())
                        .queryParam("initialAmount", BigDecimal.valueOf(-10).toString()))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAccountsInfoByCustomerIdWhenCustomerDoesNotExistReturnsExpectedResult() throws Exception {
        Customer customer = customerRepository.save(Customer.builder().name("name").surname("surname").build());
        Account account = accountRepository.save(Account.builder().customer(customer).build());

        when(transactionManagementClient.getByAccountId(account.getId())).thenReturn(new ResponseEntity<>(emptyList(), HttpStatus.OK));

        mockMvc.perform(get("/account-store/api/accounts/{customerId}", customer.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.surname").value("surname"))
                .andExpect(jsonPath("$.accountsInfo[0].id").value(account.getId().toString()));
    }

    @Test
    void getAccountsInfoByCustomerIdWhenCustomerDoesNotExistReturnsNotFoundStatus() throws Exception {
        mockMvc.perform(get("/account-store/api/accounts/{customerId}", UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }
}
