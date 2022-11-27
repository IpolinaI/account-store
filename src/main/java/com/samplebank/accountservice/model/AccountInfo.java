package com.samplebank.accountservice.model;

import com.samplebank.transactionmanagement.dto.TransactionResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class AccountInfo {
    private UUID id;

    private BigDecimal balance;

    private List<TransactionResponseDTO> transactions;
}
