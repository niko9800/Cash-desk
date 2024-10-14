package com.example.cashdesk.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CashOperationsResponseDTO {

    private Integer amount;
    private String currency;
    private String operationType;
    private LocalDateTime transactionTime;
}
