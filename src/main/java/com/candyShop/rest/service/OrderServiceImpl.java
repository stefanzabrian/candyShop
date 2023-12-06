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
    public Optional<Order> findById(Integer id) throws ResourceNotFoundException {
        return orderRepository.findById(id);

    }

    @Override
    public Order create(Integer number, Date dateOfOrder, OrderStatus orderStatus, Integer totalPrice) {
        if (number == null || dateOfOrder == null || orderStatus == null || totalPrice == null) {
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
    public Optional<Order> findByClientId(Integer id) {
        return orderRepository.findByClientId(id);
    }

    @Override
    public Order update(
            Integer id,
            Integer number,
            Date dateOfOrder,
            OrderStatus orderStatus,
            Integer totalPrice
    ) throws ResourceNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found id: " + id));

        if (number == null || dateOfOrder == null || orderStatus == null || totalPrice == null) {
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

    @Override
    public void delete(int id) throws ResourceNotFoundException {
        orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found id: " + id));
        try {
            orderRepository.deleteById(id);
        } catch (Exception e ) {
            throw new RuntimeException("Error deleting the order");
        }
    }
}
