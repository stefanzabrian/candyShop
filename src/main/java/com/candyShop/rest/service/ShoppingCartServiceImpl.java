package com.candyShop.rest.service;

import com.candyShop.rest.model.*;
import com.candyShop.rest.model.constant.OrderStatus;
import com.candyShop.rest.repository.OrderCandyRepository;
import com.candyShop.rest.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.mail.MessagingException;
import java.util.*;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private static final String ORDER_PREFIX = "CANDYORD- ";
    private final Map<Candy, Integer> cart = new LinkedHashMap<>();
    private final UserService userService;
    private final OrderCandyRepository orderCandyRepository;
    private final OrderRepository orderRepository;
    private final MailService mailService;

    @Autowired
    public ShoppingCartServiceImpl(UserService userService, OrderCandyRepository orderCandyRepository, OrderRepository orderRepository, MailService mailService) {
        this.userService = userService;
        this.orderCandyRepository = orderCandyRepository;
        this.orderRepository = orderRepository;
        this.mailService = mailService;
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
        for (Map.Entry<Candy, Integer> entry : cart.entrySet()) {
            if (entry.getKey().getPrice() == null) {
                totalPrice += entry.getValue() * entry.getKey().getPrice();
            }
        }
        return totalPrice;
    }

    @Override
    public void checkOut(String userEmail) {
        User user = userService.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email" + userEmail));
        Client client = user.getClient();
        if (client == null) {
            throw new IllegalArgumentException("User Client profile does not exists");
        }

        Order newOrder = new Order();
        newOrder.setNumber(ORDER_PREFIX + new Random().nextInt(1000000000));
        newOrder.setDateOfOrder(new Date());
        newOrder.setOrderStatus(OrderStatus.PLACED);
        newOrder.setTotalPrice(totalPrice());
        newOrder.setClient(client);
        orderRepository.save(newOrder);

        for (Map.Entry<Candy,Integer> entry : getAllCandies().entrySet()) {
            OrderCandy orderCandy = new OrderCandy();
            orderCandy.setOrder(newOrder);
            orderCandy.setCandy(entry.getKey());
            orderCandy.setQuantity(entry.getValue());
            orderCandyRepository.save(orderCandy);
        }

        try {
            mailService.sendEmail(
                    "candy-shop@gmail.com",
                    userEmail,
                    "New Candy Order",
                    "New Candy Order with total amount : " + totalPrice()
            );
        } catch (MessagingException e ) {
            e.printStackTrace();
        }
        cart.clear();

    }

    @Override
    public Map<Candy, Integer> getAllCandies() {
        return Collections.unmodifiableMap(cart);
    }
}
