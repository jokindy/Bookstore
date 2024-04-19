package com.example.bookstore.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "book_covers")
@RequiredArgsConstructor
public class BookCover extends BaseEntity {

  private byte[] content;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "book_id")
  private Book book;
}
