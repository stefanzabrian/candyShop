package com.candyShop.rest.repository;

import com.candyShop.rest.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    Optional<Order> findByClient(int id);
    Optional<Order> findById(int id);
}
