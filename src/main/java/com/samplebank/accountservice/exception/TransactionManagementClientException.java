package com.samplebank.accountservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class TransactionManagementClientException extends RuntimeException {

    private int status;

    public TransactionManagementClientException(String method, int status) {
        super(String.format("Call of %s failed with status %d", method, status));
        this.status = status;
    }
}