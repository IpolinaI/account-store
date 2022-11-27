package com.samplebank.accountservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerAccountsInfoDTO {
    private String name;

    private String surname;

    List<AccountInfoDTO> accountsInfo;
}
