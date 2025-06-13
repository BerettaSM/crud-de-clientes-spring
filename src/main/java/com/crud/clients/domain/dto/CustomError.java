package com.crud.clients.domain.dto;

import java.time.Instant;

import com.crud.clients.exceptions.ApplicationException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomError {

    private String message;
    private String path;
    private Instant timestamp;
    private Integer status;

    public CustomError(String message, String path, Integer status) {
        this(message, path, Instant.now(), status);
    }

    public static CustomError from(ApplicationException e, String path) {
        return new CustomError(e.getMessage(), path, e.getHttpStatus().value());
    }

}
