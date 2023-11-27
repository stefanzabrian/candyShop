package com.candyShop.rest.model;

import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "first_name", unique = true)
    @NotNull(message = "Must not null")
    @NotBlank(message = "Must not blank")
    private String firstName;
    @Column(name = "last_name")
    @NotNull(message = "Must not null")
    @NotBlank(message = "Must not blank")
    private String lastName;
    @Column(name = "address", columnDefinition = "TEXT")
    @NotNull(message = "Must not null")
    @NotBlank(message = "Must not blank")
    private String address;



    public Client() {
    }

    public Client(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
