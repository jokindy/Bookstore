package com.example.bookstore.error;

import static com.example.bookstore.util.CommonConstants.BOOK_COVER_SIZE;
import static com.example.bookstore.util.ExceptionErrorBodyBuilder.builder;

import com.example.bookstore.util.ErrorCode;
import com.example.bookstore.util.ExceptionErrorBodyBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@Slf4j
@RestControllerAdvice
public class ErrorController {

  @ExceptionHandler
  public ResponseEntity<Error> handleGenericException(GeneralException exception) {
    log.error(exception.getMessage(), exception);
    Error error = new Error(exception.getErrors());
    return ResponseEntity.status(exception.getStatus()).body(error);
  }

  @ExceptionHandler
  public ResponseEntity<Error> handleException(Exception exception) {
    log.error(exception.getMessage(), exception);
    Error error = new Error();
    error.setErrors(
        ExceptionErrorBodyBuilder.builder()
            .description(ErrorCode.SERVICE_UNAVAILABLE.getDescription())
            .build());
    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
  }

  @ExceptionHandler
  public ResponseEntity<Error> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException exception) {
    log.error(exception.getMessage(), exception);
    Error error = new Error(getErrors(exception));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  @ExceptionHandler
  public ResponseEntity<Error> handleMaxUploadSizeExceededException(
      MaxUploadSizeExceededException exception) {
    log.error(exception.getMessage(), exception);

    Error error =
        new Error(
            ExceptionErrorBodyBuilder.builder()
                .errorCode(ErrorCode.BS04)
                .description(ErrorCode.BS04.getDescription())
                .invalidParameter(BOOK_COVER_SIZE)
                .build());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  private List<Map<String, Object>> getErrors(MethodArgumentNotValidException exception) {
    List<Map<String, Object>> errors = new ArrayList<>();
    exception
        .getBindingResult()
        .getFieldErrors()
        .forEach(
            fieldError -> {
              List<Map<String, Object>> list =
                  builder()
                      .errorCode(ErrorCode.BS02)
                      .description(ErrorCode.BS02.getDescription())
                      .invalidParameter(fieldError.getField())
                      .build();
              errors.addAll(list);
            });

    return errors;
  }
}
