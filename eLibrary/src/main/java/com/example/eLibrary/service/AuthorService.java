package com.example.eLibrary.service;

import com.example.eLibrary.dto.AuthorDto;
import java.util.List;

public interface AuthorService {
    List<AuthorDto> getAllAuthors();
    AuthorDto getAuthorById(Long id);
    AuthorDto saveAuthor(AuthorDto authorDto);
    void updateAuthor(AuthorDto authorDto);
    void deleteAuthor(Long id);
}
