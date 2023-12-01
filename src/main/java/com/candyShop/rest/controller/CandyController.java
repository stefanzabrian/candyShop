package com.candyShop.rest.controller;

import com.candyShop.rest.controller.dto.DTOCandy;
import com.candyShop.rest.controller.exception.ResourceNotFoundException;
import com.candyShop.rest.model.Candy;
import com.candyShop.rest.service.CandyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CandyController {
    private final CandyService candyService;

    @Autowired
    public CandyController(CandyService candyService) {
        this.candyService = candyService;
    }

    @GetMapping("/candy")
    public ResponseEntity<?> getAll() {
        if (candyService.getAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No content");
        }
        List<Candy> candies = candyService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(candies);
    }

    @GetMapping("/candy/{id}")
    public ResponseEntity<?> getById(@PathVariable(value = "id") int id) {
        if (candyService.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found id: " + id);
        }
        Candy candy = candyService.findById(id).get();
        return ResponseEntity.ok(candy);
    }

    @DeleteMapping("/candy/delete/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public ResponseEntity<?> delete(@PathVariable(value = "id") int id) {
        try {
            candyService.delete(id);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Candy with deleted with id: " + id);
    }

    @PostMapping("/candy")
    public ResponseEntity<?> create(
            @Valid
            @RequestBody
            DTOCandy candy) {
        // Request body from post mapping
        // Validate input data, unique name.
        if (candyService.findByName(candy.getName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name already exist: " + candy.getName());
        }
        // add int id in case it passes the try bock
        Integer candyAfterCreatedId = null;
        // catch exceptions from service
        try {
            Candy candyAfterCreation = candyService.create(
                    candy.getName(),
                    candy.getPrice(),
                    candy.getDescription()
            );
            //save client's id in variable clientAfterCreatedId
            candyAfterCreatedId = candyAfterCreation.getId();
        } catch (IllegalArgumentException e) {
            // throw e.getMessage
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        // Return the created user as a new DTO if no catch
        DTOCandy dtoCandy = new DTOCandy(
                candyService.findById(candyAfterCreatedId).get().getId(),
                candyService.findById(candyAfterCreatedId).get().getName(),
                candyService.findById(candyAfterCreatedId).get().getPrice(),
                candyService.findById(candyAfterCreatedId).get().getDescription()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoCandy);
    }
    @PutMapping("/candy/{id}")
    public ResponseEntity<?> update(
            @PathVariable(value = "id") int id,
            @Valid @RequestBody DTOCandy candy
    ) {
        // Request body from post mapping
        // Validate input data, id must exist
        if(candyService.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Candy not found id: " + id);
        }
        // add int id in case it passes the try bock
        Integer candyAfterUpdateId = null;
        // catch exceptions from service
        try {
            // attempt to Update the candy
            Candy candyAfterUpdate = candyService.update(
                    id,
                    candy.getName(),
                    candy.getPrice(),
                    candy.getDescription()
            );
            candyAfterUpdateId = candyAfterUpdate.getId();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        DTOCandy dtoCandy = new DTOCandy(
                candyService.findById(candyAfterUpdateId).get().getId(),
                candyService.findById(candyAfterUpdateId).get().getName(),
                candyService.findById(candyAfterUpdateId).get().getPrice(),
                candyService.findById(candyAfterUpdateId).get().getDescription()
        );
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dtoCandy);
    }
}
