package com.example.user_microservice.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client extends User {
    private String department;
    private String city;
    private String address;
    private String postalCode;

    public Client() {}

    public Client(String department, String city, String address, String postalCode) {
        this.department = department;
        this.city = city;
        this.address = address;
        this.postalCode = postalCode;
    }

}
