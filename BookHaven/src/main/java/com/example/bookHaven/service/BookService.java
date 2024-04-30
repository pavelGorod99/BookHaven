package com.example.bookHaven.service;

import com.example.bookHaven.dto.BookDto;
import com.example.bookHaven.dto.BookWithAuthorsDto;
import com.example.bookHaven.model.Book;

import java.util.List;

public interface BookService {
    List<BookWithAuthorsDto> getAllBooks();
    BookDto getBookById(Long id);
    List<BookWithAuthorsDto> getAllBooksByTitle(String title);
    BookDto saveBook(BookDto bookDto);
    void updateBook(BookDto bookDto);
    void deleteBook(Long id);
}
