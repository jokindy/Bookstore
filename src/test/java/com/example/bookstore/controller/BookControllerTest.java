package com.example.bookstore.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.bookstore.domain.Book;
import com.example.bookstore.repository.BookRepository;
import java.time.Year;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.yml")
public class BookControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private BookRepository bookRepository;

  @Test
  void testGetBook() throws Exception {
    Book book = new Book();
    book.setTitle("Test title");
    book.setYearOfRelease(Year.of(1782));
    Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

    mockMvc
        .perform(get("/books/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value("Test title"))
        .andExpect(jsonPath("$.yearOfRelease").value(1782));
  }
}
