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
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;
    public Order() {
    }

    public Order(Integer number, Date dateOfOrder, OrderStatus orderStatus, Integer totalPrice) {
        this.number = number;
        this.dateOfOrder = dateOfOrder;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
