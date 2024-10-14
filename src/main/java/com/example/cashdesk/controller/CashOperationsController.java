package com.example.cashdesk.controller;

import com.example.cashdesk.dto.CashOperationsRequestDTO;
import com.example.cashdesk.dto.CashOperationsResponseDTO;
import com.example.cashdesk.service.CashOperationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/cash-operation")
@RequiredArgsConstructor
public class CashOperationsController {

    private final CashOperationsService cashOperationsService;

    @PostMapping
    public ResponseEntity<CashOperationsResponseDTO> performCashOperation(
            @Valid @RequestBody CashOperationsRequestDTO request) {

        CashOperationsResponseDTO response = cashOperationsService.performOperation(request);
        return ResponseEntity.ok(response);
    }
}
