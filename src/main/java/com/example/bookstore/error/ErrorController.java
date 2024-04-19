package com.example.bookstore.error;

import static com.example.bookstore.util.CommonConstants.BOOK_COVER_SIZE;

import com.example.bookstore.util.ErrorCode;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
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
    Error error =
        new Error(
            List.of(
                ErrorDto.builder()
                    .description(ErrorCode.SERVICE_UNAVAILABLE.getDescription())
                    .build()));
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
  public ResponseEntity<Error> handleDataIntegrityViolationException(
      DataIntegrityViolationException exception) {
    log.error(exception.getMessage(), exception);
    Error error =
        new Error(
            List.of(
                ErrorDto.builder()
                    .errorCode(ErrorCode.BS06.getCode())
                    .description(ErrorCode.BS06.getDescription())
                    .reason(exception.getMostSpecificCause().getMessage())
                    .build()));
    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }

  @ExceptionHandler
  public ResponseEntity<Error> handleConstraintViolationException(
      ConstraintViolationException exception) {
    log.error(exception.getMessage(), exception);

    List<ErrorDto> errors = new ArrayList<>();
    for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
      errors.add(
          ErrorDto.builder()
              .errorCode(ErrorCode.BS05.getCode())
              .description(ErrorCode.BS05.getDescription())
              .invalidParameter(violation.getMessage())
              .build());
    }
    Error error = new Error(errors);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  @ExceptionHandler
  public ResponseEntity<Error> handleMaxUploadSizeExceededException(
      MaxUploadSizeExceededException exception) {
    log.error(exception.getMessage(), exception);

    Error error =
        new Error(
            List.of(
                ErrorDto.builder()
                    .errorCode(ErrorCode.BS04.getCode())
                    .description(ErrorCode.BS04.getDescription())
                    .invalidParameter(BOOK_COVER_SIZE)
                    .build()));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  private List<ErrorDto> getErrors(MethodArgumentNotValidException exception) {
    List<ErrorDto> errors = new ArrayList<>();
    exception
        .getBindingResult()
        .getFieldErrors()
        .forEach(
            fieldError ->
                errors.add(
                    ErrorDto.builder()
                        .errorCode(ErrorCode.BS02.getCode())
                        .description(ErrorCode.BS02.getDescription())
                        .invalidParameter(fieldError.getField())
                        .build()));
    return errors;
  }
}
