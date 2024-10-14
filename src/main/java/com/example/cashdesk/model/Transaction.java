package com.example.cashdesk.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Transaction {
    private Integer amount;
    private String currency;
    private String operationType;
    private LocalDateTime transactionTime;

    @Override
    public String toString() {
        return String.format(
                "%s %s %s %s\n",
                amount,
                currency,
                operationType,
                transactionTime);
    }
}
