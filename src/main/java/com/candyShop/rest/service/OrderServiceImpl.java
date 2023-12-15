package com.candyShop.rest.service;

import com.candyShop.rest.controller.exception.ResourceNotFoundException;
import com.candyShop.rest.model.Client;
import com.candyShop.rest.model.Order;
import com.candyShop.rest.model.User;
import com.candyShop.rest.model.constant.OrderStatus;
import com.candyShop.rest.repository.OrderRepository;
import com.candyShop.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Order> getAllByClient(String email) throws IllegalArgumentException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Client clientProfile = user.getClient();
        if (clientProfile == null) {
            throw new IllegalArgumentException("user has not client profile yet");
        }
        List<Order> clientOrders = orderRepository.findAllByClient(clientProfile);
        if (clientOrders == null) {
            throw new IllegalArgumentException("user has no orders yet");
        } else {
            return clientOrders;
        }

    }

    @Override
    public Optional<Order> findByIdAndClient(Integer id, Client client) {
        return orderRepository.findByIdAndClient(id, client);

    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return orderRepository.findById(id);
    }


    @Override
    public void delete(int id) throws ResourceNotFoundException {
        orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found id: " + id));
        try {
            orderRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting the order");
        }
    }
}
