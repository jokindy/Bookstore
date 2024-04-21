package com.example.bookstore.error;

import static com.example.bookstore.util.CommonConstants.BOOK_COVER_SIZE;
import static com.example.bookstore.util.CommonConstants.DATE;

import com.example.bookstore.util.ErrorCode;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
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
import org.springframework.web.servlet.resource.NoResourceFoundException;

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
                buildErrorDto(null, ErrorCode.SERVICE_UNAVAILABLE.getDescription(), null, null)));
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
                buildErrorDto(
                    ErrorCode.BS06.getCode(),
                    ErrorCode.BS06.getDescription(),
                    null,
                    exception.getMostSpecificCause().getMessage())));
    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }

  @ExceptionHandler
  public ResponseEntity<Error> handleConstraintViolationException(
      ConstraintViolationException exception) {
    log.error(exception.getMessage(), exception);

    List<ErrorDto> errors = new ArrayList<>();
    for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
      errors.add(
          buildErrorDto(
              ErrorCode.BS05.getCode(),
              ErrorCode.BS05.getDescription(),
              violation.getMessage(),
              null));
    }
    Error error = new Error(errors);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  @ExceptionHandler
  public ResponseEntity<Error> handleNoResourceFoundException(NoResourceFoundException exception) {
    log.error(exception.getMessage(), exception);
    Error error =
        new Error(
            List.of(
                buildErrorDto(
                    ErrorCode.RS02.getCode(), ErrorCode.RS02.getDescription(), null, null)));
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  @ExceptionHandler
  public ResponseEntity<Error> handleMaxUploadSizeExceededException(
      MaxUploadSizeExceededException exception) {
    log.error(exception.getMessage(), exception);

    Error error =
        new Error(
            List.of(
                buildErrorDto(
                    ErrorCode.BS04.getCode(),
                    ErrorCode.BS04.getDescription(),
                    BOOK_COVER_SIZE,
                    null)));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  @ExceptionHandler
  public ResponseEntity<Error> handleDateTimeException(DateTimeParseException exception) {
    log.error(exception.getMessage(), exception);

    Error error =
        new Error(
            List.of(
                buildErrorDto(
                    ErrorCode.BS05.getCode(), ErrorCode.BS05.getDescription(), DATE, null)));
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
                    buildErrorDto(
                        ErrorCode.BS02.getCode(),
                        ErrorCode.BS02.getDescription(),
                        fieldError.getField(),
                        null)));
    return errors;
  }

  private ErrorDto buildErrorDto(
      String errorCode, String description, String invalidParameter, String reason) {
    return ErrorDto.builder()
        .errorCode(errorCode)
        .description(description)
        .invalidParameter(invalidParameter)
        .reason(reason)
        .build();
  }
}
