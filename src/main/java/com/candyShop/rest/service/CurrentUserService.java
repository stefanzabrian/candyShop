package com.candyShop.rest.service;

import com.candyShop.rest.controller.dto.CurrentUser;
import com.candyShop.rest.repository.UserInMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class CurrentUserService  implements UserDetailsService {
    private final UserInMemoryRepository repository;

    @Autowired
    public CurrentUserService(UserInMemoryRepository repository) {
        this.repository = repository;
    }

    public CurrentUser loadUserByUsername(String email) throws UsernameNotFoundException {
        final CurrentUser currentUser = repository.findUserByUsername(email);
        if (currentUser == null) {
            throw new UsernameNotFoundException("Not user in memory" + email);
        }
        return currentUser;
    }
}
