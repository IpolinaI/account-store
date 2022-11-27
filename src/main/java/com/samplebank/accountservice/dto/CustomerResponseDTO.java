package com.samplebank.accountservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CustomerResponseDTO {
    private UUID id;
    private String name;
    private String surname;
}
