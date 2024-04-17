package com.example.bookstore.error;

import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeneralException extends RuntimeException {

  private static final int DEFAULT_CODE = 503;

  private final int status;
  private final String message;
  private final List<Map<String, Object>> errors;

  public GeneralException(int status, String message, List<Map<String, Object>> errors) {
    this.status = status == 0 ? DEFAULT_CODE : status;
    this.message = message;
    this.errors = errors;
  }
}
