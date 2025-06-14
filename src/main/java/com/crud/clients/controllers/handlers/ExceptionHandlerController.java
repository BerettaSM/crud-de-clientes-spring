package com.crud.clients.controllers.handlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.crud.clients.domain.dto.CustomError;
import com.crud.clients.domain.dto.ErrorEntry;
import com.crud.clients.domain.dto.ValidationError;
import com.crud.clients.exceptions.ApplicationException;
import com.crud.clients.exceptions.CustomValidationException;
import com.crud.clients.exceptions.ExceptionUtils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExceptionHandlerController {

    private final ExceptionUtils exceptionUtils;

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<CustomError> catchAll(ApplicationException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getHttpStatus().value())
                .body(CustomError.from(e, request.getRequestURI()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValid(
            MethodArgumentNotValidException e,
            HttpServletRequest request) {
        List<ErrorEntry> errors = exceptionUtils.extractErrorsFromException(e);
        ValidationError err = ValidationError.from(errors, request.getRequestURI());
        return ResponseEntity.status(err.getStatus()).body(err);
    }

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<CustomError> customValidationException(
            CustomValidationException e,
            HttpServletRequest request) {
        List<ErrorEntry> errors = e.getErrors();
        ValidationError err = ValidationError.from(errors, request.getRequestURI());
        return ResponseEntity.status(err.getStatus()).body(err);
    }

}
