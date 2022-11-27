package com.samplebank.accountservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AccountResponseDTO {
    private UUID id;
    private UUID customerId;
}
