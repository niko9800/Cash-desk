package com.example.cashdesk.validation;

import com.example.cashdesk.validation.annotation.ValidCurrencyCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CurrencyCodeValidator implements ConstraintValidator<ValidCurrencyCode, String> {

    @Override
    public boolean isValid(String currency, ConstraintValidatorContext constraintValidatorContext) {
        return "EUR".equalsIgnoreCase(currency) || "BGN".equalsIgnoreCase(currency);
    }
}
