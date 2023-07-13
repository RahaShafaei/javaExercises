package com.example.schoolPaymentManagement.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {BigDecimalLengthValidator.class})
public @interface BigDecimalLength {
    int minLength();

    String message() default "Length must be greater or equal to {minLength}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
