package com.candyShop.rest.controller.exception;


import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

public class JwtValidationException extends AuthenticationCredentialsNotFoundException {
    public JwtValidationException(String message)  {
        super(message);
    }
}
