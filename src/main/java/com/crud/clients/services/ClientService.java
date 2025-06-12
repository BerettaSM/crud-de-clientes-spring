package com.crud.clients.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud.clients.domain.entities.Client;
import com.crud.clients.repositories.ClientRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ClientService {
    
    private final ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Client findById(Long id) {
        return clientRepository.findById(id)
            .orElseThrow();
    }

}
