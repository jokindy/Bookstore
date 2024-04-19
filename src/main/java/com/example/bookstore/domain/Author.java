package com.example.bookstore.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Getter
@Setter
@Entity
@Table(name = "authors")
@RequiredArgsConstructor
public class Author extends BaseEntity {

  private String name;

  private LocalDate birthdate;

  @Column(name = "country_of_origin")
  private String countryOfOrigin;

  @ManyToMany(mappedBy = "authors")
  @Cascade({CascadeType.MERGE})
  private Set<Book> books = new HashSet<>();
}
