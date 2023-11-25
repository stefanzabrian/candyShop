package com.candyShop.rest.model;

import com.candyShop.rest.model.constant.UserRole;
import jakarta.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user")
    private Integer id;
    @Column(name = "email", unique = true)
    @Email
    @NotNull(message = "Must not null")
    @NotBlank(message = "Must not blank")
    private String email;
    @Column(name = "password")
    @NotNull(message = "Must not null")
    @NotBlank(message = "Must not blank")
    private String password;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @OneToOne
    @JoinColumn(name = "profile_id")
    private UserProfile userProfile;

    public User() {
    }

    public User(String email, String password, UserRole role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
