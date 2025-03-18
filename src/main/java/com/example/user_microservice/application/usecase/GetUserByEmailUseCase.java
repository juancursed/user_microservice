package com.example.user_microservice.application.usecase;

import com.example.user_microservice.domain.model.User;
import com.example.user_microservice.domain.repository.UserRepository;



public class GetUserByEmailUseCase {
    private final UserRepository userRepository;

    public GetUserByEmailUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User execute(String email){
        return userRepository.findByEmail(email).orElse(null);
    }
}

