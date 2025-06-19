package com.example.user_microservice.infraestructure.clients.auth.dto;

public record AuthValidateRequest (String token){
    public AuthValidateRequest{
        if (token == null || token.isBlank()){
            throw new IllegalArgumentException("El token no pude ser nulo o estar vacio");
        }
    }
}
