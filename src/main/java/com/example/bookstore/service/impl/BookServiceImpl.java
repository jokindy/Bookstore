package com.example.bookstore.service.impl;

import static com.example.bookstore.util.CommonConstants.AUTHOR_ID;
import static com.example.bookstore.util.CommonConstants.BOOK_ID;

import com.example.bookstore.domain.Author;
import com.example.bookstore.domain.Book;
import com.example.bookstore.dto.BookDto;
import com.example.bookstore.dto.BookDtoWithoutCover;
import com.example.bookstore.dto.CreateBookRequest;
import com.example.bookstore.dto.CreateBookResponse;
import com.example.bookstore.dto.PageableResultDto;
import com.example.bookstore.dto.UpdateBookRequest;
import com.example.bookstore.error.ErrorDto;
import com.example.bookstore.error.GeneralException;
import com.example.bookstore.mapper.BookMapper;
import com.example.bookstore.repository.AuthorRepository;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.service.BookCoverService;
import com.example.bookstore.service.BookService;
import com.example.bookstore.util.ErrorCode;
import com.example.bookstore.validator.FileValidator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final AuthorRepository authorRepository;
  private final BookCoverService bookCoverService;
  private final BookRepository bookRepository;
  private final BookMapper bookMapper;

  @Override
  public PageableResultDto<BookDtoWithoutCover> getBooks(Pageable pageable) {
    return bookMapper.toPageableResultDto(bookRepository.findAll(pageable));
  }

  @Override
  public PageableResultDto<String> getUniqueBooks(Pageable pageable) {
    return bookMapper.toPageableResultStringDto(bookRepository.findDistinctBooks(pageable));
  }

  @Override
  public PageableResultDto<BookDtoWithoutCover> getBooksByAuthorName(
      String name, Pageable pageable) {
    return bookMapper.toPageableResultDto(bookRepository.findByAuthors_Name(name, pageable));
  }

  @Override
  @Transactional
  public CreateBookResponse addNewBook(CreateBookRequest createBookRequest) {
    Book book = bookMapper.toBook(createBookRequest);
    enrichBookAuthors(book, createBookRequest.getAuthorIds());
    bookRepository.save(book);

    bookCoverService.saveBookCover(book, createBookRequest.getCover());

    return bookMapper.toCreateBookResponse(book);
  }

  @Override
  @SneakyThrows
  @Transactional
  public CreateBookResponse updateBook(UpdateBookRequest updateBookRequest) {
    Book bookToUpdate = getBookById(updateBookRequest.getId());

    bookToUpdate.setTitle(updateBookRequest.getTitle());
    bookToUpdate.setYearOfRelease(updateBookRequest.getYearOfRelease());

    FileValidator.validateBookCover(updateBookRequest.getCover());
    bookToUpdate.getBookCover().setContent(updateBookRequest.getCover().getBytes());

    bookToUpdate.setAuthors(new HashSet<>());
    enrichBookAuthors(bookToUpdate, updateBookRequest.getAuthorIds());

    return bookMapper.toCreateBookResponse(bookRepository.save(bookToUpdate));
  }

  @Override
  public BookDto findBookById(Long bookId) {
    return bookMapper.toBookDto(getBookById(bookId));
  }

  @Override
  public void deleteBookById(long bookId) {
    Optional<Book> bookO = bookRepository.findById(bookId);

    bookO.ifPresentOrElse(
        bookRepository::delete,
        () -> {
          throw new GeneralException(
              HttpStatus.NOT_FOUND.value(),
              List.of(
                  ErrorDto.builder()
                      .errorCode(ErrorCode.BS01.getCode())
                      .description(ErrorCode.BS01.getDescription())
                      .invalidParameter(BOOK_ID)
                      .build()));
        });
  }

  private void enrichBookAuthors(Book book, List<Long> authorIds) {
    List<ErrorDto> errors = new ArrayList<>();
    for (Long id : authorIds) {
      Optional<Author> authorO = authorRepository.findById(id);
      if (authorO.isEmpty()) {
        errors.add(
            ErrorDto.builder()
                .errorCode(ErrorCode.BS01.getCode())
                .description(ErrorCode.BS01.getDescription())
                .invalidParameter(AUTHOR_ID)
                .build());
      } else {
        book.addAuthor(authorO.get());
      }
    }

    if (!errors.isEmpty()) {
      throw new GeneralException(HttpStatus.NOT_FOUND.value(), errors);
    }
  }

  private Book getBookById(long bookId) {
    return bookRepository
        .findById(bookId)
        .orElseThrow(
            () ->
                new GeneralException(
                    HttpStatus.NOT_FOUND.value(),
                    List.of(
                        ErrorDto.builder()
                            .errorCode(ErrorCode.BS01.getCode())
                            .description(ErrorCode.BS01.getDescription())
                            .invalidParameter(BOOK_ID)
                            .build())));
  }
}
