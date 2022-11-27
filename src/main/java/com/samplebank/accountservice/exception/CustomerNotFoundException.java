package com.samplebank.accountservice.exception;

import java.util.UUID;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(UUID customerId) {
        super(String.format("Customer with id %s is not found", customerId));
    }
}