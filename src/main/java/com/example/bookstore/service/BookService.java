package com.example.bookstore.service;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.dto.BookDtoWithoutCover;
import com.example.bookstore.dto.CreateBookRequest;
import com.example.bookstore.dto.CreateBookResponse;
import com.example.bookstore.dto.PageableResultDto;
import com.example.bookstore.dto.UpdateBookRequest;
import org.springframework.data.domain.Pageable;

public interface BookService {

  PageableResultDto<BookDtoWithoutCover> getBooks(Pageable pageable);

  PageableResultDto<String> getUniqueBooks(Pageable pageable);

  PageableResultDto<BookDtoWithoutCover> getBooksByAuthorName(String name, Pageable pageable);

  BookDto findBookById(Long bookId);

  CreateBookResponse addNewBook(CreateBookRequest createBookRequest);

  CreateBookResponse updateBook(UpdateBookRequest updateBookRequest);

  void deleteBookById(long bookId);
}
