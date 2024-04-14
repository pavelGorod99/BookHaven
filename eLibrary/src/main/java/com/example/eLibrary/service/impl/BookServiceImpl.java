package com.example.eLibrary.service.impl;

import com.example.eLibrary.dto.BookDto;
import com.example.eLibrary.model.Book;
import com.example.eLibrary.repository.BookRepository;
import com.example.eLibrary.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

//@Service
// implements BookService
public class BookServiceImpl {

//    private final BookRepository bookRepository;
//    private final ModelMapper modelMapper;
//
//    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
//        this.bookRepository = bookRepository;
//        this.modelMapper = modelMapper;
//    }
//
//    @Override
//    public List<Book> getAllBooks() {
//        return bookRepository.findAll();
//    }
//
//    @Override
//    public Book getBookById(Long id) {
//        return bookRepository.findById(id)
//                .orElseThrow(() -> new NoSuchElementException("Book not found"));
//    }
//
//    @Override
//    public Book saveBook(BookDto bookDto) {
//        Book book = modelMapper.map(bookDto, Book.class);
//        return bookRepository.save(book);
//    }
//
//    @Override
//    public void updateBook(BookDto bookDto) {
//        Optional<Book> optionalBook = bookRepository.findById(bookDto.getBookId());
//        if (optionalBook.isPresent()) {
//            Book book = modelMapper.map(bookDto, Book.class);
//            bookRepository.save(book);
//        } else {
//            new NoSuchElementException("Book not found");
//        }
//    }
//
//    public void deleteBook(Long id) {
//        bookRepository.deleteById(id);
//    }
}

