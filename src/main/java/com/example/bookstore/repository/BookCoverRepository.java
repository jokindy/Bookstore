package com.example.bookstore.repository;

import com.example.bookstore.domain.BookCover;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCoverRepository extends JpaRepository<BookCover, Long> {}
