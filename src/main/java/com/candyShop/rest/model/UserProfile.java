package com.candyShop.rest.model;

import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_profile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "first_name")
    @NotNull(message = "Must not null")
    @NotBlank(message = "Must not blank")
    private String firstName;
    @Column(name = "last_name")
    @NotNull(message = "Must not null")
    @NotBlank(message = "Must not blank")
    private String lastName;
    @Column(name = "phone_number")
    @NotNull(message = "Must not null")
    @NotBlank(message = "Must not blank")
    private Integer phoneNumber;
    @Column(name = "address")
    @NotNull(message = "Must not null")
    @NotBlank(message = "Must not blank")
    private String address;

    public UserProfile() {
    }

    public UserProfile(String firstName, String lastName, Integer phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
