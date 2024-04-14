package com.example.eLibrary.repository;

import com.example.eLibrary.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> getAuthorByFirstNameAndLastName(String firstName, String lastName);
}
