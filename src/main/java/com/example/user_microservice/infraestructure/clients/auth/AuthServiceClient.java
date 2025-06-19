package com.example.user_microservice.infraestructure.clients.auth;

import com.example.user_microservice.infraestructure.clients.auth.dto.AuthValidateRequest;
import com.example.user_microservice.infraestructure.clients.auth.dto.AuthValidateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-microservice")
public interface AuthServiceClient {
    @PostMapping("/api/auth/validate-token")
    AuthValidateResponse validateToken(@RequestBody AuthValidateRequest request);
}


