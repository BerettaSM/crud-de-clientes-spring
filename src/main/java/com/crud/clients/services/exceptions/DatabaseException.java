package com.crud.clients.services.exceptions;

import org.springframework.http.HttpStatus;

import com.crud.clients.exceptions.ApplicationException;

public class DatabaseException extends ApplicationException {

    public DatabaseException(String message, HttpStatus status) {
        super(message, status);
    }

    public DatabaseException(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }

}
