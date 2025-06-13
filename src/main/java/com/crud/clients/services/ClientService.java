package com.crud.clients.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crud.clients.domain.dto.ClientDTO;
import com.crud.clients.domain.entities.Client;
import com.crud.clients.domain.utils.CPFUtils;
import com.crud.clients.exceptions.ExceptionUtils;
import com.crud.clients.repositories.ClientRepository;
import com.crud.clients.services.exceptions.DatabaseException;
import com.crud.clients.services.exceptions.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ClientService {
    
    private final ClientRepository clientRepository;

    private final CPFUtils cpfUtils;
    private final ExceptionUtils exceptionUtils;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        return clientRepository.findById(id)
            .map(ClientDTO::from)
            .orElseThrow(() -> new ResourceNotFoundException());
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable)
            .map(ClientDTO::from);
    }

    @Transactional
    public ClientDTO save(ClientDTO dto) {
        cpfUtils.standardizeCpf(dto);
        try {
            return ClientDTO.from(clientRepository.save(dto.toEntity()));
        }
        catch (DataIntegrityViolationException e) {
            if (exceptionUtils.wasCpfViolated(e)) {
                throw new DatabaseException("CPF already registered", HttpStatus.CONFLICT);
            }
            throw new DatabaseException("Data integrity violation");
        }
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        cpfUtils.standardizeCpf(dto);
        try {
            Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
            copyDtoToEntity(dto, client);
            return ClientDTO.from(clientRepository.saveAndFlush(client));
        }
        catch (DataIntegrityViolationException e) {
            if (exceptionUtils.wasCpfViolated(e)) {
                throw new DatabaseException("CPF already registered", HttpStatus.CONFLICT);
            }
            throw new DatabaseException("Data integrity violation");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteById(Long id) {
        if(!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException();
        }
        try {
            clientRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Data integrity violation");
        }
    }

    private void copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setBirthDate(dto.getBirthDate());
        entity.setIncome(dto.getIncome());
        entity.setChildren(dto.getChildren());
    }

}
