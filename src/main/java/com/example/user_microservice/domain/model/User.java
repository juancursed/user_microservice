package com.example.user_microservice.domain.model;


import jakarta.persistence.*;
import jakarta.persistence.GenerationType;

import java.util.Date;


@Entity
@Table(name  = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private Date bornDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {}

    public User(String name, String email, Date bornDate){
        this.name = name;
        this.email = email;
        this.bornDate = bornDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
