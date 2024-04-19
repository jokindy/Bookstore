package com.example.bookstore.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.Year;
import java.util.List;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequest {

  @Size(min = 3, max = 100)
  @NotEmpty(message = "must be specified")
  private String title;

  @NotNull(message = "must be specified")
  @PastOrPresent(message = "must be in the past")
  private Year yearOfRelease;

  private MultipartFile cover;

  @NotEmpty
  private List<Long> authorIds;
}
