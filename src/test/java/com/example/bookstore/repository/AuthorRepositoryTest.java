package com.example.bookstore.repository;

import com.example.bookstore.domain.Author;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import javax.sql.DataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AuthorRepositoryTest {

  @Autowired private DataSource dataSource;
  @Autowired private JdbcTemplate jdbcTemplate;
  @Autowired private EntityManager entityManager;
  @Autowired private AuthorRepository authorRepository;

  @Test
  void injectedComponentsAreNotNull() {
    Assertions.assertNotNull(dataSource);
    Assertions.assertNotNull(jdbcTemplate);
    Assertions.assertNotNull(entityManager);
    Assertions.assertNotNull(authorRepository);
  }

  @Test
  void saveAuthor() {
    Author author = new Author();
    author.setName("Test Writer");
    author.setBirthdate(LocalDate.of(1994, 10, 10));
    authorRepository.save(author);

    Assertions.assertNotNull(authorRepository.findById(1L).get());
  }
}
