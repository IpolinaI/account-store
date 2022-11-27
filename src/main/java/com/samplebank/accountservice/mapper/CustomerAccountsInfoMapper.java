package com.samplebank.accountservice.mapper;

import com.samplebank.accountservice.dto.AccountInfoDTO;
import com.samplebank.accountservice.dto.CustomerAccountsInfoDTO;
import com.samplebank.accountservice.model.CustomerAccountsInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerAccountsInfoMapper {
    CustomerAccountsInfoDTO map(CustomerAccountsInfo customerAccountsInfoDTO);

    AccountInfoDTO map(AccountInfoDTO accountInfoDTO);
}
