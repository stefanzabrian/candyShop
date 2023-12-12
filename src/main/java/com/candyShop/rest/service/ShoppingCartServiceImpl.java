package com.candyShop.rest.service;

import com.candyShop.rest.model.Candy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCartServiceImpl implements ShoppingCartService{
    @Override
    public void addCandy(Candy candy) {

    }

    @Override
    public void removeCandy(Candy candy) {

    }

    @Override
    public void clearCandies() {

    }

    @Override
    public double totalPrice() {
        return 0;
    }

    @Override
    public void checkOut(String userEmail) {

    }

    @Override
    public Map<Candy, Integer> getAllCandies() {
        return null;
    }
}
