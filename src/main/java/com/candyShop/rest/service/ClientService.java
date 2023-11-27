package com.candyShop.rest.service;

import com.candyShop.rest.controller.exception.ResourceNotFoundException;
import com.candyShop.rest.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Optional<Client> findByFirstName(String firstName);

    Client create(
            String firstName,
            String lastName,
            String address
    );

    List<Client> getAll();

    Optional<Client> findById(int id);

    Client update(
            int id,
            String firstName,
            String lastName,
            String address
    ) throws ResourceNotFoundException;

    void delete(int id) throws ResourceNotFoundException;
}
