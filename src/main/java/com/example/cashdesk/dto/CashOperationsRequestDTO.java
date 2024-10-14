package com.example.cashdesk.dto;

import com.example.cashdesk.validation.annotation.ValidCurrencyCode;
import com.example.cashdesk.validation.annotation.ValidOperationType;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

@Data
public class CashOperationsRequestDTO implements Serializable {

    @NotNull(message = "Amount should not be null!")
    @Min(value = 1, message = "Amount should be positive number!")
    private Integer amount;

    @ValidCurrencyCode
    private String currency;

    @ValidOperationType
    private String operationType;

    @NotEmpty(message = "Denominations should be specified!")
    private Map<Integer, Integer> denominations;
}
