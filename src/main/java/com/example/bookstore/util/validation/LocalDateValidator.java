package com.example.bookstore.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class LocalDateValidator implements ConstraintValidator<ValidLocalDate, String> {

  @Override
  public void initialize(ValidLocalDate constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    try {
      System.out.println("WWWWWWWWWWWW");
      LocalDate.parse(value);
      return true;
    } catch (DateTimeParseException e) {
      System.out.println("AAAAA");
      return false;
    }
  }
}
