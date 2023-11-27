package com.candyShop.rest.controller;

import com.candyShop.rest.service.CandyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CandyController {
    private final CandyService candyService;

    @Autowired
    public CandyController(CandyService candyService) {
        this.candyService = candyService;
    }
}
