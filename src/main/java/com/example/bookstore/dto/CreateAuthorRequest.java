package com.example.bookstore.dto;

import com.example.bookstore.util.validation.ValidLocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAuthorRequest {

  @NotEmpty(message = "must be specified")
  private String name;

  @ValidLocalDate
  private String birthdate;

  private String countryOfOrigin;
}
