package com.candyShop.rest.service;

import com.candyShop.rest.controller.exception.ResourceNotFoundException;
import com.candyShop.rest.model.Candy;
import com.candyShop.rest.repository.CandyRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandyServiceImpl implements CandyService {
    private final CandyRepository candyRepository;

    @Autowired
    public CandyServiceImpl(CandyRepository candyRepository) {
        this.candyRepository = candyRepository;
    }

    @Override
    public Optional<Candy> findByFirstName(String name) {
        return candyRepository.findByName(name);
    }

    @Override
    public Candy create(String name, int price, String description) {
        // Validate input parameters
        if ((StringUtils.isEmpty(name) || price == 0 || StringUtils.isEmpty(description))) {
            throw new IllegalArgumentException("name & description must not null, price must not 0");
        }
        if ((StringUtils.isBlank(name) || StringUtils.isBlank(description))) {
            throw new IllegalArgumentException("name & description must not blank");
        }
        // Create the candy
        Candy candy = new Candy(name, price, description);

        try {
            // Save the candy to the database
            return candyRepository.save(candy);
        } catch (DataIntegrityViolationException e) {
            // Handle database constraints, such as unique constraint violations (e.g., duplicate name)
            throw new IllegalArgumentException("Name already exists", e);
        } catch (Exception e) {
            // Handle other exceptions (database issues, etc.)
            throw new RuntimeException("Error creating user", e);
        }
    }

    @Override
    public List<Candy> getAll() {
        return candyRepository.findAll();
    }

    @Override
    public Optional<Candy> findById(int id) {
        return candyRepository.findById(id);
    }

    @Override
    public Candy update(Integer id, String name, Integer price, String description) throws ResourceNotFoundException {
        // Validate if id exists in DB
        Candy candy = candyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candy not found id: " + id));
        // Validate input parameters
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(description) || price == 0) {
            throw new IllegalArgumentException("name & description must not null, price must not 0");
        }
        if (StringUtils.isBlank(name) || StringUtils.isBlank(description)) {
            throw new IllegalArgumentException("name & description must not blank");
        }
        // modify candy
        candy.setName(name);
        candy.setDescription(description);
        candy.setPrice(price);
        try {
            // Save the modified candy to the database
            return candyRepository.save(candy);
        } catch (Exception e) {
            // Handle other exceptions (database issues, etc.)
            throw new RuntimeException("Error updating the candy", e);
        }
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        candyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candy not found with id: " + id));
        candyRepository.deleteById(id);
    }
}
