package com.candyShop.rest.service;

import com.candyShop.rest.model.Candy;
import com.candyShop.rest.model.Order;
import com.candyShop.rest.model.constant.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface OrderService {
    Optional<Order> findById(String id);
    Candy create(
            int number,
            Date dateOfOrder,
            OrderStatus orderStatus,
            int totalPrice
    );
    
    List<Order> getAll();
    Optional<Order> findByClient(int id);
    Candy update(int id,
                 int number,
                 Date dateOfOrder,
                 OrderStatus orderStatus,
                 int totalPrice);
}
