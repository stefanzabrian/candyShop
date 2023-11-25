package com.candyShop.rest.repository;

import com.candyShop.rest.model.Candy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandyRepository extends JpaRepository<Candy, Integer> {
}
