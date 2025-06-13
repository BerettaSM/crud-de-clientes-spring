package com.crud.clients.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud.clients.domain.dto.ClientDTO;
import com.crud.clients.domain.entities.Client;
import com.crud.clients.repositories.ClientRepository;
import com.crud.clients.services.exceptions.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ClientService {
    
    private final ClientRepository clientRepository;

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
    public ClientDTO save(ClientDTO client) {
        return ClientDTO.from(clientRepository.save(client.toEntity()));
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        Client client = clientRepository.getReferenceById(id);
        copyDtoToEntity(dto, client);
        return ClientDTO.from(clientRepository.save(client));
    }

    @Transactional
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    private void copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setBirthDate(dto.getBirthDate());
        entity.setIncome(dto.getIncome());
        entity.setChildren(dto.getChildren());
    }

}
