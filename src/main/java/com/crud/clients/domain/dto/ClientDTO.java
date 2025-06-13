package com.crud.clients.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.BeanUtils;

import com.crud.clients.domain.entities.Client;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ClientDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 255, message = "Name must have between 2 and 255 characters")
    private String name;

    @NotBlank(message = "CPF cannot be empty")
    @CPF(message = "Invalid CPF")
    private String cpf;

    @NotNull(message = "Income must not be null")
    @Positive(message = "Income must be a positive value")
    private Double income;

    @NotNull(message = "Birth date cannot be null")
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @NotNull(message = "Children cannot be null")
    @PositiveOrZero(message = "Children must be a positive value or zero")
    @Max(value = 100, message = "Children must be at most 100")
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
