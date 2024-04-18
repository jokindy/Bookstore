package com.example.bookstore.error;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class GeneralException extends RuntimeException {

  private static final int DEFAULT_CODE = 503;

  private final int status;
  private final List<ErrorDto> errors;

  public GeneralException(int status,  List<ErrorDto> errors) {
    this.status = status;
    this.errors = errors;
  }
}
