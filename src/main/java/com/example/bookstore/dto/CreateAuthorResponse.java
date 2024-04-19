package com.example.bookstore.dto;

import java.time.LocalDate;

public record CreateAuthorResponse(long id, String name, LocalDate birthdate, String countryOfOrigin) {}
