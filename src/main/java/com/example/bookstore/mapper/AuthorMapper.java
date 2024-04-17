package com.example.bookstore.mapper;

import com.example.bookstore.domain.Author;
import com.example.bookstore.dto.CreateAuthorRequest;
import com.example.bookstore.dto.CreateAuthorResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

  Author toAuthor(CreateAuthorRequest createAuthorRequest);

  CreateAuthorResponse toAuthorDto(Author author);
}
