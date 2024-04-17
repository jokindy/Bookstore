package com.example.bookstore.validator;

import static com.example.bookstore.util.CommonConstants.BOOK_COVER;
import static com.example.bookstore.util.CommonConstants.BOOK_COVER_EXTENSION;
import static com.example.bookstore.util.CommonConstants.NO_AUTHOR_FOUND;
import static java.util.Objects.isNull;

import com.example.bookstore.error.GeneralException;
import com.example.bookstore.util.ErrorCode;
import com.example.bookstore.util.ExceptionErrorBodyBuilder;
import java.util.*;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

public class FileValidator {

  public static void validateBookCover(MultipartFile file) {
    List<Map<String, Object>> errors = new ArrayList<>();

    if (isNull(file) || file.isEmpty()) {
      errors.addAll(
          ExceptionErrorBodyBuilder.builder()
              .errorCode(ErrorCode.BS03)
              .description(ErrorCode.BS03.getDescription())
              .invalidParameter(BOOK_COVER)
              .build());
    }

    if (isNull(file.getOriginalFilename()) || !isValidExtension(file.getOriginalFilename())) {
      errors.addAll(
          ExceptionErrorBodyBuilder.builder()
              .errorCode(ErrorCode.BS04)
              .description(ErrorCode.BS04.getDescription())
              .invalidParameter(BOOK_COVER_EXTENSION)
              .build());
    }

    if (!errors.isEmpty()) {
      throw new GeneralException(HttpStatus.BAD_REQUEST.value(), NO_AUTHOR_FOUND, errors);
    }
  }

  private static boolean isValidExtension(String fileName) {
    final String fileExtension =
        Objects.requireNonNull(FilenameUtils.getExtension(fileName)).toLowerCase();
    return Arrays.stream(FileExtension.values())
        .anyMatch(extension -> extension.value.equals(fileExtension));
  }

  enum FileExtension {
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png");

    final String value;

    FileExtension(String value) {
      this.value = value;
    }
  }
}
