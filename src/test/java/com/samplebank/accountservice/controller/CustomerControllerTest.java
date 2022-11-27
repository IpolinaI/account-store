package com.samplebank.accountservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samplebank.accountservice.client.TransactionManagementClient;
import com.samplebank.accountservice.dto.CustomerRequestDTO;
import com.samplebank.accountservice.mapper.repository.AccountRepository;
import com.samplebank.accountservice.mapper.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @MockBean
    private TransactionManagementClient transactionManagementClient;

    @Test
    void createWhenRequestIsValidReturnsExpectedResult() throws Exception {
        CustomerRequestDTO customer = CustomerRequestDTO.builder().name("name").surname("surname").build();

        mockMvc.perform(post("/account-store/api/customers")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.surname").value("surname"));
    }

    @Test
    void createWhenRequestIsNotValidReturnsBadRequestStatus() throws Exception {
        CustomerRequestDTO customer = CustomerRequestDTO.builder().surname("surname").build();

        mockMvc.perform(post("/account-store/api/customers")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isBadRequest());
    }
}
