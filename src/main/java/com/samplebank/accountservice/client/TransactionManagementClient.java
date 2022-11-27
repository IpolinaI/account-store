package com.samplebank.accountservice.client;

import com.samplebank.accountservice.config.TransactionManagementClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.samplebank.transactionmanagement.dto.TransactionRequestDTO;
import com.samplebank.transactionmanagement.dto.TransactionResponseDTO;

import java.util.List;
import java.util.UUID;

@FeignClient(value = "transaction-management-client",
        url = "${client.transaction-management.url}",
        path = "/transaction-management/api",
        configuration = TransactionManagementClientConfig.class)
public interface TransactionManagementClient {
    @RequestMapping(method = RequestMethod.POST, value = "/transactions")
    void create(@RequestBody TransactionRequestDTO transactionRequestDTO);

    @RequestMapping(method = RequestMethod.GET, value = "/transactions/{accountId}")
    ResponseEntity<List<TransactionResponseDTO>> getByAccountId(@PathVariable("accountId") UUID accountId);
}
