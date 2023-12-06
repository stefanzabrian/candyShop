package com.candyShop.rest.service;

import com.candyShop.rest.controller.exception.ResourceNotFoundException;
import com.candyShop.rest.model.Order;
import com.candyShop.rest.model.constant.OrderStatus;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface OrderService {
    Optional<Order> findById(Integer id) throws ResourceNotFoundException;
    Order create(
            Integer number,
            Date dateOfOrder,
            OrderStatus orderStatus,
            Integer totalPrice
    );

    List<Order> getAll();
    Optional<Order> findByClientId(Integer id);
    Order update(Integer id,
                 Integer number,
                 Date dateOfOrder,
                 OrderStatus orderStatus,
                 Integer totalPrice) throws ResourceNotFoundException;
    void delete(int id) throws ResourceNotFoundException;
}
