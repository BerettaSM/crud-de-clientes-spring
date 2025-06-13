package com.crud.clients.services.exceptions;

import org.springframework.http.HttpStatus;

import com.crud.clients.exceptions.ApplicationException;

public class ResourceNotFoundException extends ApplicationException {

    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

    public ResourceNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }

    public ResourceNotFoundException() {
        this("Resource not found");
    }

}
