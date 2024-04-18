package com.example.bookstore.util.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Min(value = 0, message = "Minimum value is 1")
@Max(value = 2147483647, message = "Maximum value is 2147483647")
public @interface ValidPageNumber {

  String message() default "page number";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
