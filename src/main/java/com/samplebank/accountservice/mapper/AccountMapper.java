package com.samplebank.accountservice.mapper;

import com.samplebank.accountservice.dto.AccountResponseDTO;
import com.samplebank.accountservice.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(source = "customer.id", target = "customerId")
    AccountResponseDTO map(Account account);
}
