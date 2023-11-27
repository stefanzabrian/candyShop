package com.candyShop.rest.service;

import com.candyShop.rest.controller.exception.ResourceNotFoundException;
import com.candyShop.rest.model.Candy;

import java.util.List;
import java.util.Optional;

public interface CandyService {
    Optional<Candy> findByFirstName(String name);

    Candy create(
            String name,
            int price,
            String description
    );

    List<Candy> getAll();

    Optional<Candy> findById(int id);

    Candy update(
            Integer id,
            String name,
            Integer price,
            String description
    ) throws ResourceNotFoundException;
    void delete(Integer id) throws ResourceNotFoundException;
}
