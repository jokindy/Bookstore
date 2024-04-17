package com.example.bookstore.service;

import com.example.bookstore.domain.Book;
import org.springframework.web.multipart.MultipartFile;

public interface BookCoverService {

    void saveBookCover(Book book, MultipartFile cover);
}
