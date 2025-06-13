package com.crud.clients.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.ConstraintViolationException.ConstraintKind;
import org.springframework.dao.DataIntegrityViolationException;

public abstract class ExceptionUtils {

    public static boolean wasCpfViolated(DataIntegrityViolationException e) {
        return e.getCause() instanceof ConstraintViolationException ee &&
            ee.getKind().equals(ConstraintKind.UNIQUE) &&
            ee.getConstraintName().contains("UNIQUE_CPF");
    }

}
