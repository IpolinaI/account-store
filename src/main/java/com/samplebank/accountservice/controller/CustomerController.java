package com.samplebank.accountservice.controller;

import com.samplebank.accountservice.dto.CustomerRequestDTO;
import com.samplebank.accountservice.dto.CustomerResponseDTO;
import com.samplebank.accountservice.mapper.CustomerMapper;
import com.samplebank.accountservice.model.Customer;
import com.samplebank.accountservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account-store/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerResponseDTO> create(@RequestBody CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerService.createCustomer(customerMapper.map(customerRequestDTO));
        return new ResponseEntity(customerMapper.map(customer), HttpStatus.OK);
    }
}
