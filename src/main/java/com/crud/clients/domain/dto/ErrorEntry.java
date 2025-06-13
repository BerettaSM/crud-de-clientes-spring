package com.crud.clients.domain.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Getter;

@Getter
public class ErrorEntry {

    private final String fieldName;
    private final List<String> messages = new ArrayList<>();

    public ErrorEntry(String fieldName, List<String> messages) {
        this.fieldName = fieldName;
        this.messages.addAll(messages);
    }

    public ErrorEntry(Map.Entry<String, List<String>> entry) {
        this(entry.getKey(), entry.getValue());
    }
    
}
