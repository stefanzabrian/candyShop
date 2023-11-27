package com.candyShop.rest.service;

import com.candyShop.rest.model.Admin;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface AdminService extends UserDetailsService {
    void save(String email,
              String password);

    Optional<Admin> findByEmail(String email);
}
