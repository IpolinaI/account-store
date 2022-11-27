package com.samplebank.accountservice.mapper;

import com.samplebank.accountservice.dto.CustomerRequestDTO;
import com.samplebank.accountservice.dto.CustomerResponseDTO;
import com.samplebank.accountservice.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer map(CustomerRequestDTO customerRequestDTO);

    CustomerResponseDTO map(Customer customer);
}
