package com.candyShop.rest.service;

import com.candyShop.rest.controller.exception.ResourceNotFoundException;
import com.candyShop.rest.model.Order;
import com.candyShop.rest.model.constant.OrderStatus;
import com.candyShop.rest.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<Order> findById(int id) throws ResourceNotFoundException {
        return orderRepository.findById(id);

    }

    @Override
    public Order create(int number, Date dateOfOrder, OrderStatus orderStatus, int totalPrice) {
        if (number == 0 || dateOfOrder == null || orderStatus == null || totalPrice == 0) {
            throw new IllegalArgumentException("number, date of order, order status and total price must not null");
        }

        Order order = new Order(
                number,
                dateOfOrder,
                orderStatus,
                totalPrice
        );

        try {
            return orderRepository.save(order);
        } catch (Exception e) {
            throw new RuntimeException("Error creating the order");
        }
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findByClient(int id) {
        return orderRepository.findByClient(id);
    }

    @Override
    public Order update(
            int id,
            int number,
            Date dateOfOrder,
            OrderStatus orderStatus,
            int totalPrice
    ) throws ResourceNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found id: " + id));

        if (number == 0 || dateOfOrder == null || orderStatus == null || totalPrice == 0) {
            throw new IllegalArgumentException("number, date of order, order status and total price must not null");
        }

        order.setId(id);
        order.setNumber(number);
        order.setDateOfOrder(dateOfOrder);
        order.setOrderStatus(orderStatus);
        order.setTotalPrice(totalPrice);
        try {
            return orderRepository.save(order);
        } catch (Exception e) {
            throw new RuntimeException("Error updating the order");
        }
    }
}
