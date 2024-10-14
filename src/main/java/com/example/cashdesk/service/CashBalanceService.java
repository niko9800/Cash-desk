package com.example.cashdesk.service;

import com.example.cashdesk.model.Balance;
import com.example.cashdesk.dto.CashBalanceResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CashBalanceService {

    private final FileOperationService fileOperationService;

    public List<CashBalanceResponseDTO> readBalances() {
        List<Balance> balances = fileOperationService.readBalances();
        return balances.stream().map(b -> mapBalanceToDTO(b)).collect(Collectors.toList());
    }

    private CashBalanceResponseDTO mapBalanceToDTO(Balance balance) {
        return CashBalanceResponseDTO.builder()
                .amount(balance.getAmount())
                .currency(balance.getCurrency())
                .denominations(balance.getDenominations())
                .build();
    }
}
