package com.candyShop.rest.model;

import com.candyShop.rest.model.constant.OrderStatus;
import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "number")
    @NotNull(message = "Must not null")
    @NotBlank(message = "Must not blank")
    private Integer number;
    @Column(name = "date_of_order")
    @NotNull(message = "Must not null")
    @NotBlank(message = "Must not blank")
    private Date dateOfOrder;
    @Column(name = "order_status")
    private OrderStatus orderStatus;
    @Column(name = "total_price")
    @NotNull(message = "Must not null")
    @NotBlank(message = "Must not blank")
    private Integer totalPrice;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Order() {
    }

    public Order(Integer number, Date dateOfOrder, OrderStatus orderStatus, Integer totalPrice) {
        this.number = number;
        this.dateOfOrder = dateOfOrder;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
