package com.example.cashdesk.controller;

import com.example.cashdesk.dto.CashBalanceResponseDTO;
import com.example.cashdesk.service.CashBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cash-balance")
@RequiredArgsConstructor
public class CashBalanceController {

    private final CashBalanceService cashBalanceService;

    @GetMapping
    ResponseEntity<List<CashBalanceResponseDTO>> getCurrentBalances() {
        List<CashBalanceResponseDTO> balanceResponse = cashBalanceService.readBalances();
        return ResponseEntity.ok(balanceResponse);
    }
}
