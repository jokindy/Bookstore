package com.example.bookstore.util.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LocalDateValidator.class)
@Documented
public @interface ValidLocalDate {
    String message() default "Invalid date format or out of range (yyyy-mm-dd)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
