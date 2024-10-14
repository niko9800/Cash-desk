package com.example.cashdesk.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class CashBalanceResponseDTO {
    private Integer amount;
    private String currency;
    private Map<Integer, Integer> denominations;
}
