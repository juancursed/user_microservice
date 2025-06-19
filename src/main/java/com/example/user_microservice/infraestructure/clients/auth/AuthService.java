package com.example.user_microservice.infraestructure.clients.auth;

import com.example.user_microservice.infraestructure.clients.auth.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtUtils jwtUtils;

    @Autowired
    public AuthService(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    public boolean isValidToken(String token) {
        // Limpiar el token (remover "Bearer " si está presente)
        String cleanToken = token.replace("Bearer ", "");

        return jwtUtils.validateToken(cleanToken);
    }

    // Método adicional si necesitas obtener información del token
    public String getUsernameFromToken(String token) {
        String cleanToken = token.replace("Bearer ", "");
        return jwtUtils.extractUsername(cleanToken);
    }
}