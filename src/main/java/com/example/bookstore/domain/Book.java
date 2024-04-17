package com.example.bookstore.domain;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Getter
@Setter
@Entity
@Table(name = "books")
@RequiredArgsConstructor
public class Book extends BaseEntity {

  private String title;

  @Column(name = "year_of_release")
  private Year yearOfRelease;

  @OneToOne(mappedBy = "book", orphanRemoval = true)
  @PrimaryKeyJoinColumn
  @Cascade(CascadeType.ALL)
  private BookCover bookCover;

  @ManyToMany
  @Cascade(CascadeType.MERGE)
  @JoinTable(
      name = "book_author",
      joinColumns = @JoinColumn(name = "book_id"),
      inverseJoinColumns = @JoinColumn(name = "author_id"))
  private Set<Author> authors = new HashSet<>();

  public void addAuthor(Author author) {
    authors.add(author);
  }
}
