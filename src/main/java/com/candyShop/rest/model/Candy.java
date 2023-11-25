package com.candyShop.rest.model;

import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "candy")
public class Candy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    @NotNull(message = "Must not null")
    @NotBlank(message = "Must not blank")
    private String name;
    @Column(name = "price")
    @NotNull(message = "Must not null")
    @NotBlank(message = "Must not blank")
    private Integer price;
    @Column(name = "descpription")
    @NotNull(message = "Must not null")
    @NotBlank(message = "Must not blank")
    private String description;

    public Candy() {
    }

    public Candy(String name, Integer price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
