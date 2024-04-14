package com.example.eLibrary.service;

import com.example.eLibrary.dto.BookDto;
import com.example.eLibrary.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book saveBook(BookDto bookDto);
    void updateBook(BookDto bookDto);
    void deleteBook(Long id);
}
