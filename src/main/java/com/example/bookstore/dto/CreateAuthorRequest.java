package com.example.bookstore.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAuthorRequest {

  @NotEmpty(message = "must be specified")
  private String name;

  @Past private LocalDate birthdate;
  private String countryOfOrigin;
}
