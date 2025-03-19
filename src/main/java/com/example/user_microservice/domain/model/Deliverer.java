package com.example.user_microservice.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "deliverers")
public class Deliverer extends User {
    private String license;
    private String vehicle;

    public Deliverer() {}

    public Deliverer(String license, String vehicle) {
        this.license = license;
        this.vehicle = vehicle;
    }

}
