package com.example.cashdesk.exception.handler;

import com.example.cashdesk.exception.CashBalanceErrorResponse;
import com.example.cashdesk.exception.CashBalanceException;
import com.example.cashdesk.exception.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApplicationExceptionHandler {

    private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationError(MethodArgumentNotValidException e) {
        Map<String, String> validationErrors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            validationErrors.put(fieldName, error.getDefaultMessage());
        });

        ValidationErrorResponse response = new ValidationErrorResponse();
        response.setErrors(validationErrors);
        response.setTimestamp(LocalDateTime.now());
        response.setHttpStatus(BAD_REQUEST);
        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    @ExceptionHandler(value = CashBalanceException.class)
    public ResponseEntity<Object> handleCashBalanceException(CashBalanceException e) {
        CashBalanceErrorResponse response = new CashBalanceErrorResponse();
        response.setHttpStatus(BAD_REQUEST);
        response.setMessage(e.getMessage());
        response.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(response, BAD_REQUEST);
    }
}
