package com.example.bookstore.validator;

import static com.example.bookstore.util.CommonConstants.BOOK_COVER;
import static com.example.bookstore.util.CommonConstants.BOOK_COVER_EXTENSION;
import static java.util.Objects.isNull;

import com.example.bookstore.error.ErrorDto;
import com.example.bookstore.error.GeneralException;
import com.example.bookstore.util.ErrorCode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileValidator {

  public void validateBookCover(MultipartFile file) {
    List<ErrorDto> errors = new ArrayList<>();

    if (isNull(file) || file.isEmpty()) {
      errors.add(
          ErrorDto.builder()
              .errorCode(ErrorCode.BS03.getCode())
              .description(ErrorCode.BS03.getDescription())
              .invalidParameter(BOOK_COVER)
              .build());
    } else if (isNull(file.getOriginalFilename())
        || !isValidExtension(file.getOriginalFilename())) {
      errors.add(
          ErrorDto.builder()
              .errorCode(ErrorCode.BS04.getCode())
              .description(ErrorCode.BS04.getDescription())
              .invalidParameter(BOOK_COVER_EXTENSION)
              .build());
    }

    if (!errors.isEmpty()) {
      throw new GeneralException(HttpStatus.BAD_REQUEST.value(), errors);
    }
  }

  private boolean isValidExtension(String fileName) {
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
