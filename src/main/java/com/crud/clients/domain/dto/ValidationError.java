package com.crud.clients.domain.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ValidationError extends CustomError {

    private final List<ErrorEntry> errors = new ArrayList<>();

    public ValidationError(String message, String path, Instant timestamp, List<ErrorEntry> errors) {
        super(message, path, timestamp, HttpStatus.UNPROCESSABLE_ENTITY.value());
        this.errors.addAll(errors);
    }

    public ValidationError(String message, String path, List<ErrorEntry> errors) {
        this(message, path, Instant.now(), errors);
    }

    public void addError(String fieldName, String message) {
        errors.stream()
            .filter(err -> err.getFieldName().equals(fieldName))
            .findFirst()
            .ifPresentOrElse(
                entry -> entry.getMessages().add(message),
                () -> errors.add(new ErrorEntry(fieldName, List.of(message))));
    }
    
    public static ValidationError from(List<ErrorEntry> errors, String path) {
        return new ValidationError("Validation error(s)", path, errors);
    }

}
