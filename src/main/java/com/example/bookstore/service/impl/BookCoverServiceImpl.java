package com.example.bookstore.service.impl;

import static com.example.bookstore.util.CommonConstants.BOOK_COVER;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookCover;
import com.example.bookstore.error.ErrorDto;
import com.example.bookstore.error.GeneralException;
import com.example.bookstore.repository.BookCoverRepository;
import com.example.bookstore.service.BookCoverService;
import com.example.bookstore.util.ErrorCode;
import com.example.bookstore.validator.FileValidator;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class BookCoverServiceImpl implements BookCoverService {

  private final BookCoverRepository bookCoverRepository;
  private final FileValidator fileValidator;

  @Override
  @Transactional
  public void saveBookCover(Book book, MultipartFile cover) {
    fileValidator.validateBookCover(cover);

    try {
      BookCover bookCover = new BookCover();
      bookCover.setContent(cover.getBytes());
      bookCover.setBook(book);
      bookCoverRepository.save(bookCover);
    } catch (IOException e) {
      throw new GeneralException(
          HttpStatus.BAD_REQUEST.value(),
          List.of(
              ErrorDto.builder()
                  .errorCode(ErrorCode.BS04.getCode())
                  .description(ErrorCode.BS04.getDescription())
                  .invalidParameter(BOOK_COVER)
                  .build()));
    }
  }
}
