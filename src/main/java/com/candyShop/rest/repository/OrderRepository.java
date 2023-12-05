package com.candyShop.rest.repository;

import com.candyShop.rest.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    Optional<Order> findByClient(int id);
}
