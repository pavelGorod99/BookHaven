package com.example.bookHaven.service.impl;

import com.example.bookHaven.dto.*;
import com.example.bookHaven.model.Author;
import com.example.bookHaven.model.Book;
import com.example.bookHaven.repository.AuthorRepository;
import com.example.bookHaven.repository.BookRepository;
import com.example.bookHaven.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookWithAuthorsDto> getAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        System.out.println(bookList);
        List<BookWithAuthorsDto> bookDtoList = new ArrayList<>();
        bookList.forEach(book -> {
            System.out.println("BOOK => ");
            System.out.println(book.toString());
            BookWithAuthorsDto bookDto = new BookWithAuthorsDto();
            bookDto.setBookId(book.getBookId());
            bookDto.setTitle(book.getTitle());
            bookDto.setGenre(book.getGenre());
            bookDto.setPrice(book.getPrice());
            bookDto.setQuantity(book.getQuantity());
            Set<BookAuthorDto> authors = new HashSet<>();
            book.getAuthors().forEach(author -> {
                BookAuthorDto authorDto = new BookAuthorDto();
                authorDto.setFirstName(author.getFirstName());
                authorDto.setLastName(author.getLastName());
                authors.add(authorDto);
            });
            bookDto.setAuthors(authors);
            bookDtoList.add(bookDto);
        });
        return bookDtoList;
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found"));
        BookDto bookDto = modelMapper.map(book, BookDto.class);
        return bookDto;
    }

    @Override
    public List<BookWithAuthorsDto> getAllBooksByTitle(String title) {
        List<Book> books = bookRepository.findAllBooksByTitle(title);
        System.out.println(books);

        List<BookWithAuthorsDto> bookWithAuthorsDto = new ArrayList<>();
        books.forEach(book -> {
            BookWithAuthorsDto book1 = new BookWithAuthorsDto();
            book1.setBookId(book.getBookId());
            book1.setTitle(book.getTitle());
            book1.setGenre(book.getGenre());
            book1.setPrice(book.getPrice());
            book1.setQuantity(book.getQuantity());

            Set<BookAuthorDto> authorDtoSet = new HashSet<>();
            book.getAuthors().forEach(author -> {
                BookAuthorDto authorDto = new BookAuthorDto();
                authorDto.setFirstName(author.getFirstName());
                authorDto.setLastName(author.getLastName());
                authorDtoSet.add(authorDto);
            });
            book1.setAuthors(authorDtoSet);
            bookWithAuthorsDto.add(book1);
        });
        return bookWithAuthorsDto;
    }

    @Override
    public BookDto saveBook(BookDto bookDto) {
        Book book = new Book();

        book.setTitle(bookDto.getTitle());
        book.setGenre(bookDto.getGenre());
        book.setPrice(bookDto.getPrice());
        book.setQuantity(bookDto.getQuantity());
        book.setInTrend(bookDto.isInTrend());

        createAuthorList(bookDto, book);

        System.out.println("BOOK: ");
        System.out.println(book);

        book = bookRepository.save(book);
        bookDto.setBookId(book.getBookId());
        return bookDto;
    }

    @Override
    public void updateBook(BookDto bookDto) {
        Optional<Book> optionalBook = bookRepository.findById(bookDto.getBookId());
        if (optionalBook.isPresent()) {

            Book book = optionalBook.get();
            book.setTitle(bookDto.getTitle());
            book.setPrice(bookDto.getPrice());
            book.setQuantity(bookDto.getQuantity());
            book.setGenre(bookDto.getGenre());
            book.setInTrend(bookDto.isInTrend());

            createAuthorList(bookDto, book);

            bookRepository.save(book);
        } else {
            new NoSuchElementException("Book not found");
        }
    }

    private void createAuthorList(BookDto bookDto, Book book) {
        Set<Author> authors = new HashSet<>();
        for (Long authorId : bookDto.getAuthors()) {
            Optional<Author> authorOptional = authorRepository.findById(authorId);
            if (authorOptional.isPresent()) {
                authors.add(authorOptional.get());
            }
        }
        book.setAuthors(authors);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}

