package com.samplebank.accountservice.dto;

import lombok.Getter;
import lombok.Setter;
import com.samplebank.transactionmanagement.dto.TransactionResponseDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AccountInfoDTO {
    private UUID id;

    private BigDecimal balance;

    List<TransactionResponseDTO> transactions;
}
