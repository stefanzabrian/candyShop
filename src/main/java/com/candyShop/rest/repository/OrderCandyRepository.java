package com.candyShop.rest.repository;

import com.candyShop.rest.model.OrderCandy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCandyRepository extends JpaRepository<OrderCandy, Integer> {
}
