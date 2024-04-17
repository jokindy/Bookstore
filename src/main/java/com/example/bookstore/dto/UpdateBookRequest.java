package com.example.bookstore.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.Year;
import java.util.List;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookRequest {

  @NotNull(message = "must be specified")
  private Long id;

  @Size(min = 3, max = 100)
  @NotEmpty(message = "must be specified")
  private String title;

  @NotNull(message = "must be specified")
  private Year yearOfRelease;

  @NotNull(message = "must be specified")
  private List<Long> authorIds;

  @NotNull(message = "must be specified")
  private MultipartFile cover;
}
