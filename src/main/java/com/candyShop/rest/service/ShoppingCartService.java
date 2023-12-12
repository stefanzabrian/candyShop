package com.candyShop.rest.service;

import com.candyShop.rest.model.Candy;

import java.util.Map;

public interface ShoppingCartService {
    void addCandy(Candy candy);

    void removeCandy(Candy candy);
    void clearCandies();
    double totalPrice();
    void checkOut(String userEmail);
    Map<Candy, Integer> getAllCandies();
}
