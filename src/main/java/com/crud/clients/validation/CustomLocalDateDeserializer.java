package com.crud.clients.validation;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.crud.clients.domain.dto.ErrorEntry;
import com.crud.clients.exceptions.CustomValidationException;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

public class CustomLocalDateDeserializer extends JsonDeserializer<LocalDate> implements ContextualDeserializer {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE;

    private String fieldName;

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        if (property != null) {
            fieldName = property.getName();
        }
        return this;
    }

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        String localDateString = p.getValueAsString();
        try {
            return LocalDate.parse(localDateString, FORMATTER);
        }
        catch (DateTimeParseException ignore) {
            List<ErrorEntry> errors = List.of(new ErrorEntry(fieldName, List.of("Invalid date format")));
            CustomValidationException e = CustomValidationException.withErrors(errors);
            throw e;
        }
    }

}
