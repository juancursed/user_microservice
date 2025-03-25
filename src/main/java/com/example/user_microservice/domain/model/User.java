package com.example.user_microservice.domain.model;


import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date bornDate;
    private String phone;
    private String license;
    private String vehicle;
    private int accessLevel;
    private String department;
    private String city;
    private String address;
    private String postalCode;

    @Enumerated(EnumType.STRING)
    private Role role;

}
