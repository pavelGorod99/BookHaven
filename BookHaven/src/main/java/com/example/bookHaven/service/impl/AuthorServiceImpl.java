package com.example.bookHaven.service.impl;

import com.example.bookHaven.dto.AuthorDto;
import com.example.bookHaven.exceptions.ResourceNotFoundException;
import com.example.bookHaven.model.Author;
import com.example.bookHaven.repository.AuthorRepository;
import com.example.bookHaven.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    private ModelMapper modelMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        List<Author> authorList = authorRepository.findAll();
        List<AuthorDto> authorDtoList = new ArrayList<>();
        authorList.forEach(author -> {
            AuthorDto authorDto = modelMapper.map(author, AuthorDto.class);
            authorDtoList.add(authorDto);
        });
        return authorDtoList;
    }

    @Override
    public AuthorDto getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Author", "id", id.toString())
        );
        AuthorDto authorDto = modelMapper.map(author, AuthorDto.class);
        return authorDto;
    }

    @Override
    public AuthorDto saveAuthor(AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        author = authorRepository.save(author);
        authorDto = modelMapper.map(author, AuthorDto.class);
        return authorDto;
    }

    @Override
    public void updateAuthor(AuthorDto authorDto) {
        String firstName = authorDto.getFirstName();
        String lastName = authorDto.getLastName();

        Author author = authorRepository.getAuthorByFirstNameAndLastName(firstName, lastName).orElseThrow(
                () -> new ResourceNotFoundException("Author", "firstName and lastName", firstName + " " + lastName)
        );
        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());
        author.setBooks(authorDto.getBooks());
        authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
