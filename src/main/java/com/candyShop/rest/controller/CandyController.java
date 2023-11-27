package com.candyShop.rest.controller;

import com.candyShop.rest.controller.exception.ResourceNotFoundException;
import com.candyShop.rest.model.Candy;
import com.candyShop.rest.service.CandyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CandyController {
    private final CandyService candyService;

    @Autowired
    public CandyController(CandyService candyService) {
        this.candyService = candyService;
    }

    @GetMapping("/allCandies")
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
}
