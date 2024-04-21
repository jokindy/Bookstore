package com.example.bookstore.controller;

import com.example.bookstore.dto.CreateAuthorRequest;
import com.example.bookstore.dto.CreateAuthorResponse;
import com.example.bookstore.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequestMapping(path = "/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {

  private final AuthorService authorService;

  @PostMapping
  public ResponseEntity<CreateAuthorResponse> addNewAuthor(
      @Valid @RequestBody CreateAuthorRequest createAuthorRequest) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(authorService.addNewAuthor(createAuthorRequest));
  }
}
