package com.crud.clients.controllers.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.crud.clients.domain.dto.CustomError;
import com.crud.clients.exceptions.ApplicationException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {
    
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<CustomError> catchAll(ApplicationException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getHttpStatus().value())
            .body(CustomError.from(e, request.getRequestURI()));
    }

}
