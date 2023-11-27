package com.candyShop.rest.controller;

import com.candyShop.rest.model.Candy;
import com.candyShop.rest.service.CandyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        if(candyService.getAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No content");
        }
        List<Candy> candies = candyService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(candies);
    }
}
