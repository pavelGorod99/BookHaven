package com.example.bookHaven.controller;

import com.example.bookHaven.dto.AuthorDto;
import com.example.bookHaven.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // END-POINT FOR STUFF AND ADMIN
    @GetMapping("/")
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        List<AuthorDto> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    // END-POINT FOR STUFF AND ADMIN
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Long id) {
        AuthorDto authorDto = authorService.getAuthorById(id);
        return ResponseEntity.status(HttpStatus.OK).body(authorDto);
    }

    // END-POINT FOR STUFF AND ADMIN
    @PostMapping("/")
    public ResponseEntity<AuthorDto> saveAuthor(@RequestBody AuthorDto authorDto) {
        System.out.println("AUTHOR:");
        System.out.println(authorDto.toString());
        AuthorDto authorDto1 = authorService.saveAuthor(authorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(authorDto1);
    }

    // END-POINT FOR STUFF AND ADMIN
    @PutMapping("/")
    public ResponseEntity<Void> updateAuthor(@RequestBody AuthorDto authorDto) {
        authorService.updateAuthor(authorDto);
        return ResponseEntity.ok().build();
    }

    // END-POINT FOR STUFF AND ADMIN
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok().build();
    }
}
