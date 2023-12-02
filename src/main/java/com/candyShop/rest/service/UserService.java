package com.candyShop.rest.service;

import com.candyShop.rest.controller.exception.ResourceNotFoundException;
import com.candyShop.rest.model.User;
import com.candyShop.rest.model.constant.UserRole;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> findByEmail(String email);

    List<User> getAll();

    Optional<User> findById(int id);

    void delete(int id) throws ResourceNotFoundException;

    User create(User user);

    User update(
            int id,
            String email,
            String password
    ) throws ResourceNotFoundException;
}
