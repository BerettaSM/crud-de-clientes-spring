package com.crud.clients.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import com.crud.clients.domain.entities.Client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ClientDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String name;
    private String cpf;
    private Double income;
    private LocalDate birthDate;
    private Integer children;

    public static ClientDTO from(Client client) {
        ClientDTO dto = new ClientDTO();
        BeanUtils.copyProperties(client, dto);
        return dto;
    }

    public Client toEntity() {
        return new Client(null, name, cpf, income, birthDate, children);
    }
    
}
