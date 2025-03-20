package com.example.user_microservice.application.usecase;

import com.example.user_microservice.domain.model.User;
import com.example.user_microservice.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetUserByIdUseCase {
    private final UserRepository userRepository;

    public GetUserByIdUseCase(UserRepository userRepository){
        this.userRepository  = userRepository;
    }

    public User execute(UUID id){
        return userRepository.findById(id).orElse(null);
    }
}
