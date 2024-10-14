package com.example.cashdesk.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ValidationErrorResponse {

    private HttpStatus httpStatus;
    private Map<String, String> errors;
    private LocalDateTime timestamp;
}
