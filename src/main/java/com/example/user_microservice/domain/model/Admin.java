package com.example.user_microservice.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "admins")
public class Admin extends User {
    private int accessLevel;

    public Admin() {}

    public Admin(int accessLevel) {
        this.accessLevel = accessLevel;
    }

}
