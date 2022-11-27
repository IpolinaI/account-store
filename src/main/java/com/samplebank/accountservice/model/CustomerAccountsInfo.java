package com.samplebank.accountservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerAccountsInfo {
    private String name;

    private String surname;

    List<AccountInfo> accountsInfo;
}
