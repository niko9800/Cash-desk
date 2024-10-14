package com.example.cashdesk.validation;

import com.example.cashdesk.validation.annotation.ValidOperationType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OperationTypeValidatior implements ConstraintValidator<ValidOperationType, String> {

    @Override
    public boolean isValid(String operationType, ConstraintValidatorContext constraintValidatorContext) {
        return "DEPOSIT".equalsIgnoreCase(operationType) || "WITHDRAWAL".equalsIgnoreCase(operationType);
    }
}
