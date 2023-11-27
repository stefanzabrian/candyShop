package com.candyShop.rest.repository;

import com.candyShop.rest.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Optional<Admin> findByEmail(String email);
}
