package com.candyShop.rest.service;

import com.candyShop.rest.model.Admin;
import com.candyShop.rest.repository.AdminRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

public class AdminServiceImpl implements AdminService{
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminServiceImpl(AdminRepository adminRepository, BCryptPasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(String email, String password) {
        Admin admin = new Admin(
                email,
                passwordEncoder.encode(password)

        );
        adminRepository.save(admin);
    }

    @Override
    public void saveNo(String email, String password) {
        Admin admin = new Admin(
                email,
                password

        );
        adminRepository.save(admin);
    }
    }

    @Override
    public Optional<Admin> findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
        return new org.springframework.security.core.userdetails.User(
                admin.getEmail(),
                admin.getPassword(),
                List.of(new SimpleGrantedAuthority(admin.getEmail()))
        );
    }
}
