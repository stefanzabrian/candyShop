package com.candyShop.rest.repository;

import com.candyShop.rest.model.Client;
import com.candyShop.rest.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findAllByClient(Client client);
    Optional<Order> findByClientId(Integer id);
    Optional<Order> findById(Integer id);
    Optional<Order> findByIdAndClient(Integer id, Client client);
}
