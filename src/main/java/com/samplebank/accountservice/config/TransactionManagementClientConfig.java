package com.samplebank.accountservice.config;

import com.samplebank.accountservice.client.TransactionManagementClientErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionManagementClientConfig {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new TransactionManagementClientErrorDecoder();
    }
}
