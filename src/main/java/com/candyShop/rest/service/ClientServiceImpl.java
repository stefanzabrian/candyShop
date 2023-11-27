package com.candyShop.rest.service;

import com.candyShop.rest.controller.exception.ResourceNotFoundException;
import com.candyShop.rest.model.Client;
import com.candyShop.rest.repository.ClientRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public Optional<Client> findByFirstName(String firstName) {
        return clientRepository.findByFirstName(firstName);
    }

    @Override
    public Client create(String firstName, String lastName, String address) {
        // Validate input parameters
        if (StringUtils.isEmpty(firstName) || StringUtils.isEmpty(lastName) || StringUtils.isEmpty(address)) {
            throw new IllegalArgumentException("first name, last name, and address cannot be null");
        }
        if (StringUtils.isBlank(firstName) || StringUtils.isBlank(lastName) || StringUtils.isBlank(address)) {
            throw new IllegalArgumentException("first name, last name, and address cannot be empty");
        }
        // Create the user
        Client client = new Client(firstName, lastName, address);

        try {
            // Save the user to the database
            return clientRepository.save(client);
        } catch (DataIntegrityViolationException e) {
            // Handle database constraints, such as unique constraint violations (e.g., duplicate first name)
            throw new IllegalArgumentException("first name already exists", e);
        } catch (Exception e) {
            // Handle other exceptions (database issues, etc.)
            throw new RuntimeException("Error creating user", e);
        }
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> findById(int id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client update(int id, String firstName, String lastName, String address) throws ResourceNotFoundException {
        // Validate if id exists in DB
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        // Validate input parameters
        if (StringUtils.isEmpty(firstName) || StringUtils.isEmpty(lastName) || StringUtils.isEmpty(address)) {
            throw new IllegalArgumentException("first name, last name, and address cannot be null");
        }
        if (StringUtils.isBlank(firstName) || StringUtils.isBlank(lastName) || StringUtils.isBlank(address)) {
            throw new IllegalArgumentException("first name, last name, and address cannot be empty");
        }
        // modify client
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setAddress(address);


        try {
            // Save the modified client to the database
            return clientRepository.save(client);
        } catch (Exception e) {
            // Handle other exceptions (database issues, etc.)
            throw new RuntimeException("Error updating the client", e);
        }
    }

    @Override
    public void delete(int id) throws ResourceNotFoundException {
        clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        clientRepository.deleteById(id);
    }
}