package com.example.bookstore.dto;

import java.time.Year;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDtoWithoutCover {
  private String title;
  private Year yearOfRelease;
  private Set<AuthorDto> authors;
}
