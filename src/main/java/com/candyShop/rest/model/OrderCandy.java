package com.candyShop.rest.model;

import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "order_candy")
public class OrderCandy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "quantity")
    @NotNull(message = "Must not null")
    @NotBlank(message = "Must not blank")
    private Integer quantity;
    @Column(name = "candy_price")
    @NotNull(message = "Must not null")
    @NotBlank(message = "Must not blank")
    private Integer candyPrice;
    @ManyToOne
    @JoinColumn(name = "candy_id")
    private Candy candy;
    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Order order;

    public OrderCandy() {
    }

    public OrderCandy(Integer quantity, Integer candyPrice) {
        this.quantity = quantity;
        this.candyPrice = candyPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCandyPrice() {
        return candyPrice;
    }

    public void setCandyPrice(Integer candyPrice) {
        this.candyPrice = candyPrice;
    }

    public Candy getCandy() {
        return candy;
    }

    public void setCandy(Candy candy) {
        this.candy = candy;
    }


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
