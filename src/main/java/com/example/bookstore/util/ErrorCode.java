package com.example.bookstore.util;

import lombok.Getter;

@Getter
public enum ErrorCode {
  SERVICE_UNAVAILABLE("", "Service temporary unavailable"),
  BS01("BS01", "Parameter is invalid (id)"),
  BS02("BS02", "Parameter is invalid (dto field)"),
  BS03("BS03", "Required parameter is missing"),
  BS04("BS04", "Parameter is invalid (cover)");

  private final String code;
  private final String description;

  ErrorCode(String code, String description) {
    this.code = code;
    this.description = description;
  }
}
