package com.candyShop.rest.controller;

import com.candyShop.rest.config.security.JWTGenerator;
import com.candyShop.rest.controller.dto.AuthResponseDto;
import com.candyShop.rest.controller.dto.LoginDto;
import com.candyShop.rest.controller.dto.RegisterDto;
import com.candyShop.rest.model.Role;
import com.candyShop.rest.model.User;
import com.candyShop.rest.repository.RoleRepository;
import com.candyShop.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;


@RestController
@RequestMapping("/api/auth/")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final UserService userService;
    private final JWTGenerator jwtGenerator;


    @Autowired
    public AuthController(
            AuthenticationManager authenticationManager,
            RoleRepository roleRepository,
            UserService userService,
            JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(
            @RequestBody LoginDto loginDto
    ) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);

    }

    @PostMapping("register")
    public ResponseEntity<String> registerUser(
            @RequestBody RegisterDto registerDto) {
        if (userService.findByEmail(registerDto.getEmail()).isPresent()) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));


        userService.create(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

    @PostMapping("register/moderator")
    public ResponseEntity<String> registerModerator(
            @RequestBody RegisterDto registerDto) {
        if (userService.findByEmail(registerDto.getEmail()).isPresent()) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());

        Role roles = roleRepository.findByName("MODERATOR").get();
        user.setRoles(Collections.singletonList(roles));


        userService.create(user);

        return new ResponseEntity<>("Moderator registered success!", HttpStatus.OK);
    }

    @PostMapping("register/admin")
    public ResponseEntity<String> registerAdmin(
            @RequestBody RegisterDto registerDto) {
        if (userService.findByEmail(registerDto.getEmail()).isPresent()) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());

        Role roles = roleRepository.findByName("ADMIN").get();
        user.setRoles(Collections.singletonList(roles));


        userService.create(user);

        return new ResponseEntity<>("Moderator registered success!", HttpStatus.OK);
    }

}
