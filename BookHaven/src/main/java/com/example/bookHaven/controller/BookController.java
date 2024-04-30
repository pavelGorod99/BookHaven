package com.example.bookHaven.controller;

import com.example.bookHaven.dto.BookDto;
import com.example.bookHaven.dto.BookWithAuthorsDto;
import com.example.bookHaven.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // END-POINT FOR EVERYONE
    @GetMapping("/")
    public ResponseEntity<List<BookWithAuthorsDto>> getAllBooks() {
        System.out.println("GET ALL BOOKS");
        List<BookWithAuthorsDto> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    // END-POINT FOR EVERYONE
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        BookDto book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    // END-POINT FOR EVERYONE
    @GetMapping("/by-title")
    public ResponseEntity<List<BookWithAuthorsDto>> getAllBooksByTile(@RequestParam String title) {
        List<BookWithAuthorsDto> books = bookService.getAllBooksByTitle(title);
        return ResponseEntity.ok(books);
    }

    // END-POINT FOR STUFF AND ADMIN
    @PostMapping("/")
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        System.out.println(bookDto);
        BookDto savedBook = bookService.saveBook(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    // END-POINT FOR STUFF AND ADMIN
    @PutMapping("/")
    public ResponseEntity<Void> updateBook(@RequestBody BookDto bookDto) {
        System.out.println(bookDto);
        bookService.updateBook(bookDto);
        return ResponseEntity.ok().build();
    }

    // END-POINT FOR STUFF AND ADMIN
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}

