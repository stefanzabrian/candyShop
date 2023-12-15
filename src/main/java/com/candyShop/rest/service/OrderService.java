package com.candyShop.rest.service;

import com.candyShop.rest.controller.exception.ResourceNotFoundException;
import com.candyShop.rest.model.Client;
import com.candyShop.rest.model.Order;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface OrderService {
    List<Order> getAllByClient(String email) throws IllegalArgumentException;
    Optional<Order> findByIdAndClient(Integer id, Client client);
    List<Order> getAll();
    Optional<Order> findById(Integer id);
    void delete(int id) throws ResourceNotFoundException;
}
