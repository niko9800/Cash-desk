package com.example.cashdesk.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class CashBalanceErrorResponse {
    private HttpStatus httpStatus;
    private String message;
    private LocalDateTime timeStamp;
}
