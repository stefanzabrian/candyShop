package com.candyShop.rest.controller;

import com.candyShop.rest.controller.exception.ResourceNotFoundException;
import com.candyShop.rest.model.Order;
import com.candyShop.rest.service.OrderService;
import com.candyShop.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
        
    }

    @GetMapping("/order/client-orders")
    public ResponseEntity<?> allClientOrders(Principal principal) {
        if (orderService.getAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No content");
        }
        List<Order> orders = orderService.getAllByClient(principal.getName());
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @GetMapping("/order/all")
    public ResponseEntity<?> allOrders() {
        List<Order> allOrders = orderService.getAll();
        if (allOrders == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No orders yet");
        }
        return ResponseEntity.status(HttpStatus.OK).body(allOrders);
    }

    @DeleteMapping("/order/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        try {
            Optional<Order> order = orderService.findById(id);
            if (order.isPresent()) {
                orderService.delete(id);
                return ResponseEntity.status(HttpStatus.OK).body("Order deleted");
            }
        } catch (ResourceNotFoundException e ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return null;
    }

}
