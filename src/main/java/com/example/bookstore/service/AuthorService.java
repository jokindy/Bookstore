package com.example.bookstore.service;

import com.example.bookstore.dto.CreateAuthorRequest;
import com.example.bookstore.dto.CreateAuthorResponse;

public interface AuthorService {

    CreateAuthorResponse addNewAuthor(CreateAuthorRequest createAuthorRequest);
}
