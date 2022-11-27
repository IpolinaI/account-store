package com.samplebank.accountservice.client;

import com.samplebank.accountservice.exception.TransactionManagementClientException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class TransactionManagementClientErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        return new TransactionManagementClientException(methodKey, response.status());
    }
}