package com.crud.clients.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.crud.clients.domain.dto.ErrorEntry;

import lombok.Getter;

@Getter
public class CustomValidationException extends ApplicationException {

    private final List<ErrorEntry> errors = new ArrayList<>();

    public CustomValidationException(String message, List<ErrorEntry> errors) {
        super(message, HttpStatus.UNPROCESSABLE_ENTITY);
        this.errors.addAll(errors);
    }
    
    public static CustomValidationException withErrors(List<ErrorEntry> errors) {
        return new CustomValidationException("Validation error(s)", errors);
    }

}
