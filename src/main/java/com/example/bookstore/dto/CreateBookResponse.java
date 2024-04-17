package com.example.bookstore.dto;

import java.time.Year;
import java.util.Set;

public record CreateBookResponse(
    long id, String title, Year yearOfRelease, Set<AuthorDto> authors, BookCoverDto bookCover) {}
