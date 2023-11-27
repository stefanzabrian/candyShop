package com.candyShop.rest.repository;

import com.candyShop.rest.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByFirstName(String firstName);
    Optional<Client> findById(int id);
}
