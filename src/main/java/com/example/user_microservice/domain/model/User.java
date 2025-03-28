package com.example.user_microservice.domain.model;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "El nombre es obligatorio")
    private String firstName;

    @NotBlank(message = "El Apellido es obligatorio")
    private String lastName;

    @NotBlank(message = "El Email es obligatorio")
    @Email(message = "Debe ser un correo válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    @Temporal(TemporalType.DATE)
    private Date bornDate;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "\\+?[0-9. ()-]{7,25}", message = "Debe ser un número de teléfono válido")
    private String phone;

    @Nullable
    private String license;

    @Nullable
    private String vehicle;

    @Min(value = 0, message = "El nivel de acceso no puede ser negativo")
    private int accessLevel;

    @NotBlank(message = "El departamento es obligatorio")
    private String department;

    @NotBlank(message = "La ciudad es obligatorio")
    private String city;

    @NotBlank(message = "La direccion es obligatorio")
    private String address;

    @NotBlank(message = "El codigo Postal es obligatorio")
    private String postalCode;

    @NotNull(message = "El rol es obligatorio")
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {}

    public User(String name, String email, Date bornDate){
        this.name = name;
        this.email = email;
        this.bornDate = bornDate;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
