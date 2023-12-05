package com.candyShop.rest.service;

import com.candyShop.rest.controller.exception.ResourceNotFoundException;
import com.candyShop.rest.model.Order;
import com.candyShop.rest.model.constant.OrderStatus;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface OrderService {
    Optional<Order> findById(int id) throws ResourceNotFoundException;
    Order create(
            int number,
            Date dateOfOrder,
            OrderStatus orderStatus,
            int totalPrice
    );

    List<Order> getAll();
    Optional<Order> findByClient(int id);
    Order update(int id,
                 int number,
                 Date dateOfOrder,
                 OrderStatus orderStatus,
                 int totalPrice) throws ResourceNotFoundException;
    void delete(int id) throws ResourceNotFoundException;
}
