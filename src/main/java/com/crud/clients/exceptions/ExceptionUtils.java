package com.crud.clients.exceptions;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.ConstraintViolationException.ConstraintKind;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.crud.clients.domain.dto.ErrorEntry;

@Component
public class ExceptionUtils {

    public boolean wasCpfViolated(DataIntegrityViolationException e) {
        return e.getCause() instanceof ConstraintViolationException ee &&
                ee.getKind().equals(ConstraintKind.UNIQUE) &&
                ee.getConstraintName().contains("UNIQUE_CPF");
    }

    public List<ErrorEntry> extractErrorsFromException(MethodArgumentNotValidException e) {
        Map<String, List<String>> errors = e.getFieldErrors()
            .stream()
            .collect(
                Collectors.groupingBy(
                    FieldError::getField,
                    Collectors.mapping(
                        FieldError::getDefaultMessage,
                        Collectors.toList())));
        return errors.entrySet()
            .stream()
            .map(ErrorEntry::new)
            .toList();
    }

}
