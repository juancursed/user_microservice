package com.example.user_microservice.domain.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"  // Esta propiedad en el JSON indica la subclase
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Client.class, name = "client")
        // Puedes agregar más subtipos si es necesario, por ejemplo:
        // @JsonSubTypes.Type(value = Repartidor.class, name = "repartidor"),
        // @JsonSubTypes.Type(value = Administrador.class, name = "admin")
})
public abstract class User {
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
