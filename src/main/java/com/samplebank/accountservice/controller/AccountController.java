package com.samplebank.accountservice.controller;

import com.samplebank.accountservice.dto.AccountResponseDTO;
import com.samplebank.accountservice.dto.CustomerAccountsInfoDTO;
import com.samplebank.accountservice.mapper.AccountMapper;
import com.samplebank.accountservice.mapper.CustomerAccountsInfoMapper;
import com.samplebank.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/account-store/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;
    private final CustomerAccountsInfoMapper customerAccountsInfoMapper;

    @PostMapping(path = "/{customerId}")
    public ResponseEntity<AccountResponseDTO> create(@PathVariable("customerId") UUID customerId, BigDecimal initialAmount) {
        return new ResponseEntity(accountMapper.map(accountService.createAccount(customerId, initialAmount)), HttpStatus.OK);
    }

    @GetMapping(path = "/{customerId}")
    public ResponseEntity<CustomerAccountsInfoDTO> getAccountsInfoByCustomerId(@PathVariable("customerId") UUID customerId) {
        return new ResponseEntity(customerAccountsInfoMapper.map(accountService.getAccountsInfoByCustomerId(customerId)), HttpStatus.OK);
    }
}
