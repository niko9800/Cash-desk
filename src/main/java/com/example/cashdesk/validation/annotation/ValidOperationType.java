package com.example.cashdesk.validation.annotation;

import com.example.cashdesk.validation.CurrencyCodeValidator;
import com.example.cashdesk.validation.OperationTypeValidatior;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = OperationTypeValidatior.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidOperationType {
    String message() default "Invalid operation type!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
