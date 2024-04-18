package com.example.bookstore.controller;

import com.example.bookstore.dto.*;
import com.example.bookstore.service.BookService;
import com.example.bookstore.util.validation.ValidPageNumber;
import com.example.bookstore.util.validation.ValidPageSize;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping(path = "/books")
@RequiredArgsConstructor
public class BookController {

  private final BookService bookService;

  @GetMapping
  public ResponseEntity<PageableResultDto<BookDtoWithoutCover>> findAllBooks(
      @RequestParam(required = false, defaultValue = "20") @ValidPageSize Integer pageSize,
      @RequestParam(required = false, defaultValue = "0") @ValidPageNumber Integer pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    return ResponseEntity.status(HttpStatus.OK).body(bookService.getBooks(pageable));
  }

  @GetMapping("/name")
  public ResponseEntity<PageableResultDto<BookDtoWithoutCover>> findAllBooksByAuthorName(
      @RequestParam String authorName,
      @RequestParam(required = false, defaultValue = "20") @ValidPageSize Integer pageSize,
      @RequestParam(required = false, defaultValue = "0") @ValidPageNumber Integer pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    return ResponseEntity.status(HttpStatus.OK)
        .body(bookService.getBooksByAuthorName(authorName, pageable));
  }

  @GetMapping("/unique")
  public ResponseEntity<PageableResultDto<String>> findAllUniqueBooks(
      @RequestParam(required = false, defaultValue = "20") @ValidPageSize Integer pageSize,
      @RequestParam(required = false, defaultValue = "0") @ValidPageNumber Integer pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    return ResponseEntity.status(HttpStatus.OK).body(bookService.getUniqueBooks(pageable));
  }

  @GetMapping("/{bookId}")
  public ResponseEntity<BookDto> findBookById(@PathVariable long bookId) {
    return ResponseEntity.status(HttpStatus.OK).body(bookService.findBookById(bookId));
  }

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<CreateBookResponse> addNewBook(
      @Valid @ModelAttribute CreateBookRequest createBookRequest) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(bookService.addNewBook(createBookRequest));
  }

  @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<CreateBookResponse> updateBook(
      @Valid @ModelAttribute UpdateBookRequest updateBookRequest) {
    return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(updateBookRequest));
  }

  @DeleteMapping("/{bookId}")
  public ResponseEntity<Void> deleteBook(@PathVariable long bookId) {
    bookService.deleteBookById(bookId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
