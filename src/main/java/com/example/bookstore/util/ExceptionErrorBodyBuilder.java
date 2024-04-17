package com.example.bookstore.util;

import static java.util.Objects.nonNull;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionErrorBodyBuilder {

  private static final String ERROR_CODE = "errorCode";
  private static final String DESCRIPTION = "description";
  private static final String INVALID_PARAMETER = "invalidParameter";

  public static ErrorBodyBuilder builder() {
    return new ErrorBodyBuilder();
  }

  @NoArgsConstructor
  public static class ErrorBodyBuilder {

    private ErrorCode errorCode;
    private String description;
    private Object invalidParameter;

    public ErrorBodyBuilder errorCode(ErrorCode errorCode) {
      this.errorCode = errorCode;
      return this;
    }

    public ErrorBodyBuilder description(String description) {
      this.description = description;
      return this;
    }

    public ErrorBodyBuilder invalidParameter(Object invalidParameter) {
      this.invalidParameter = invalidParameter;
      return this;
    }

    public List<Map<String, Object>> build() {
      Map<String, Object> errorBody = new LinkedHashMap<>(4, 1);
      if (nonNull(errorCode)) {
        errorBody.put(ERROR_CODE, errorCode.getCode());
        errorBody.put(DESCRIPTION, errorCode.getDescription());
      }
      if (nonNull(description)) {
        errorBody.put(DESCRIPTION, description);
      }
      if (nonNull(invalidParameter)) {
        errorBody.put(INVALID_PARAMETER, invalidParameter);
      }
      return List.of(errorBody);
    }
  }
}
