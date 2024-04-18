package com.example.bookstore.repository;

import com.example.bookstore.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  Page<Book> findByAuthors_Name(String authorName, Pageable pageable);

  @Query("SELECT DISTINCT b.title FROM Book b")
  Page<String> findDistinctBooks(Pageable pageable);
}
