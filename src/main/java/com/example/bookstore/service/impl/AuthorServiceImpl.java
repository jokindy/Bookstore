package com.example.bookstore.service.impl;

import com.example.bookstore.domain.Author;
import com.example.bookstore.dto.CreateAuthorRequest;
import com.example.bookstore.dto.CreateAuthorResponse;
import com.example.bookstore.mapper.AuthorMapper;
import com.example.bookstore.repository.AuthorRepository;
import com.example.bookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;
  private final AuthorMapper authorMapper;

  @Override
  public CreateAuthorResponse addNewAuthor(CreateAuthorRequest createAuthorRequest) {
    Author author = authorMapper.toAuthor(createAuthorRequest);
    return authorMapper.toAuthorDto(authorRepository.save(author));
  }
}
