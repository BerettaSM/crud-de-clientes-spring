package com.crud.clients.domain.utils;

import org.springframework.stereotype.Component;

import com.crud.clients.domain.dto.ClientDTO;

@Component
public class CPFUtils {

    public void standardizeCpf(ClientDTO dto) {
        dto.setCpf(dto.getCpf().replaceAll("\\D", ""));
    }

}
