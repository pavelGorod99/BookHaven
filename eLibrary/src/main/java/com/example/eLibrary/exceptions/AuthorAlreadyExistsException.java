package com.example.eLibrary.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AuthorAlreadyExistsException extends RuntimeException {

    public AuthorAlreadyExistsException(String message) {
        super(message);
    }
}
