package com.samplebank.accountservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerRequestDTO {
    private String name;
    private String surname;
}
