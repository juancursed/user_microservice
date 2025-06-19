package com.example.user_microservice.infraestructure.clients.auth.dto;

public record AuthValidateResponse(boolean valid, String userId) {
}
