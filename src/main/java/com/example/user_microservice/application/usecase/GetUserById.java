package com.example.user_microservice.application.usecase;

import com.example.user_microservice.domain.model.User;
import com.example.user_microservice.domain.repository.UserRepository;

import java.util.UUID;

public class GetUserById {
    private final UserRepository userRepository;

    public GetUserById(UserRepository userRepository){
        this.userRepository  = userRepository;
    }

    public User execute(UUID id){
        return userRepository.findById(id).orElse(null);
    }
}
