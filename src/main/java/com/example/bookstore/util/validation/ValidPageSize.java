package com.example.bookstore.util.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Min(value = 1, message = "Minimum value is 1")
@Max(value = 100, message = "Maximum value is 100")
@ReportAsSingleViolation
public @interface ValidPageSize {

  String message() default "page size";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
