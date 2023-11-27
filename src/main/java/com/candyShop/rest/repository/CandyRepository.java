package com.candyShop.rest.repository;

import com.candyShop.rest.model.Candy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CandyRepository extends JpaRepository<Candy,Integer> {
    Optional<Candy> findByName(String name);
    Optional<Candy> findById(int id);
}
