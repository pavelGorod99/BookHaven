package com.example.bookHaven.service;

import com.example.bookHaven.dto.BookDto;
import com.example.bookHaven.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book saveBook(BookDto bookDto);
    void updateBook(BookDto bookDto);
    void deleteBook(Long id);
}
