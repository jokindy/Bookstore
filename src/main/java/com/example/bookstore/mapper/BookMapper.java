package com.example.bookstore.mapper;

import com.example.bookstore.domain.Book;
import com.example.bookstore.dto.BookDto;
import com.example.bookstore.dto.BookDtoWithoutCover;
import com.example.bookstore.dto.CreateBookRequest;
import com.example.bookstore.dto.CreateBookResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper extends CommonMapper<BookDtoWithoutCover, Book> {

  Book toBook(CreateBookRequest createBookRequest);

  BookDto toBookDto(Book book);

  CreateBookResponse toCreateBookResponse(Book book);
}
