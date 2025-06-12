package com.crud.clients.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.clients.domain.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
