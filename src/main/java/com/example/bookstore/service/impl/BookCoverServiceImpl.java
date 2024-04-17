package com.example.bookstore.service.impl;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookCover;
import com.example.bookstore.repository.BookCoverRepository;
import com.example.bookstore.service.BookCoverService;
import com.example.bookstore.validator.FileValidator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class BookCoverServiceImpl implements BookCoverService {

  private final BookCoverRepository bookCoverRepository;

  @Override
  @SneakyThrows
  @Transactional
  public void saveBookCover(Book book, MultipartFile cover) {
    BookCover bookCover = new BookCover();
    FileValidator.validateBookCover(cover);
    bookCover.setContent(cover.getBytes());
    bookCover.setBook(book);
    bookCoverRepository.save(bookCover);
  }


}
