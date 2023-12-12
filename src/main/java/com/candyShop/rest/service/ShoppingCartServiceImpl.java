package com.candyShop.rest.service;

import com.candyShop.rest.model.Candy;
import com.candyShop.rest.repository.OrderCandyRepository;
import com.candyShop.rest.repository.OrderRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private static final String ORDER_PREFIX = "CANDYORD- ";
    private final Map<Candy, Integer> cart = new LinkedHashMap<>();
    private final UserService userService;
    private final OrderCandyRepository orderCandyRepository;
    private final OrderRepository orderRepository;

    public ShoppingCartServiceImpl(UserService userService, OrderCandyRepository orderCandyRepository, OrderRepository orderRepository) {
        this.userService = userService;
        this.orderCandyRepository = orderCandyRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void addCandy(Candy candy) {
        if (cart.containsKey(candy)) {
            cart.put(candy, cart.get(candy) + 1);
        } else {
            cart.put(candy, 1);
        }
    }

    @Override
    public void removeCandy(Candy candy) {
        if (cart.containsKey(candy)) {
            if (cart.get(candy) == 1) {
                cart.remove(candy);
            } else {
                cart.put(candy, cart.get(candy) - 1);
            }
        }
    }

    @Override
    public void clearCandies() {
        cart.clear();

    }

    @Override
    public double totalPrice() {
        double totalPrice = 0;
        for (Map.Entry<Candy,Integer> entry : cart.entrySet()) {
            if (entry.getKey().getPrice() == null)  {
                totalPrice += entry.getValue() * entry.getKey().getPrice();
            }
        }
        return totalPrice;
    }

    @Override
    public void checkOut(String userEmail) {

    }

    @Override
    public Map<Candy, Integer> getAllCandies() {
        return Collections.unmodifiableMap(cart);
    }
}
