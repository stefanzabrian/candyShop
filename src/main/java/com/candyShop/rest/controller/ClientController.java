package com.candyShop.rest.controller;

import com.candyShop.rest.controller.dto.DTOClient;
import com.candyShop.rest.controller.exception.ResourceNotFoundException;
import com.candyShop.rest.model.Client;
import com.candyShop.rest.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping("/allClients")
    public ResponseEntity<?> getAll() {
        if (clientService.getAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Clients yet");
        }

        List<Client> clients = clientService.getAll();

        return ResponseEntity.ok(clients);
    }


    @GetMapping("/client/{id}")
    public ResponseEntity<?> getById(@PathVariable(value = "id") int id) {
        if (clientService.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found id: " + id);
        }
        Client client = clientService.findById(id).get();
        return ResponseEntity.ok(client);
    }

    // mapping POST Client
    // RequestBody & validate input,
    // catch exceptions & execute database if all good
    // ResponseEntity with status and msg.
    @PostMapping("/client/create")
    public ResponseEntity<?> create(
            @Valid
            @RequestBody
            DTOClient client) {
        // Request body from post mapping
        // Validate input data, unique first name.
        if (clientService.findByFirstName(client.getFirstName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("First name already exists");
        }
        // add int id in case it passes the try bock
        Integer clientAfterCreatedId = null;
        // catch exceptions from service
        try {
            // attempt to Create the client
            Client clientAfterCreation = clientService.create(
                    client.getFirstName(),
                    client.getLastName(),
                    client.getAddress()
            );
            //save client's id in variable clientAfterCreatedId
            clientAfterCreatedId = clientAfterCreation.getId();

        } catch (IllegalArgumentException e) {
            // throw e.getMessage
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        // Return the created user as a new DTO if no catch
        DTOClient dtoClient = new DTOClient(
                clientService.findById(clientAfterCreatedId).get().getId(),
                clientService.findById(clientAfterCreatedId).get().getFirstName(),
                clientService.findById(clientAfterCreatedId).get().getLastName(),
                clientService.findById(clientAfterCreatedId).get().getAddress()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoClient);
    }

    // mapping PUT Client
    // RequestBody & validate input,
    // catch exceptions & execute database if all good
    // ResponseEntity with status and msg.
    @PutMapping("/client/{id}")
    public ResponseEntity<?> update(
            @PathVariable(value = "id") int id,
            @Valid @RequestBody DTOClient client
    ) {
        // Request body from post mapping
        // Validate input data, id must exist
        if (clientService.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client with id: " + id + " not found");
        }
        // add int id in case it passes the try bock
        Integer clientAfterUpdateId = null;
        // catch exceptions from service
        try {
            // attempt to Update the client
            Client clientAfterUpdate = clientService.update(
                    id,
                    client.getFirstName(),
                    client.getLastName(),
                    client.getAddress()
            );
            // save client's id in variable clientAfterCreationId
            clientAfterUpdateId = clientAfterUpdate.getId();
        } catch (ResourceNotFoundException e) {
            // throw e.getMessage
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        // Return the created user as a new DTO if no catch
        DTOClient dtoClient = new DTOClient(
                clientService.findById(clientAfterUpdateId).get().getId(),
                clientService.findById(clientAfterUpdateId).get().getFirstName(),
                clientService.findById(clientAfterUpdateId).get().getLastName(),
                clientService.findById(clientAfterUpdateId).get().getAddress()
        );
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dtoClient);

    }

    @DeleteMapping("/client/delete/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public ResponseEntity<?> delete(@PathVariable(value = "id") int id) {
        try {
            clientService.delete(id);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Client with id: " + id + " was deleted");
    }
}