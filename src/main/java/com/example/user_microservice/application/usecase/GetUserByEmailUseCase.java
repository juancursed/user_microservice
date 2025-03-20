package com.example.user_microservice.application.usecase;

import com.example.user_microservice.domain.model.User;
import com.example.user_microservice.domain.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class GetUserByEmailUseCase {
    private final UserRepository userRepository;

    public GetUserByEmailUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User execute(String email){
        return userRepository.findByEmail(email).orElse(null);
    }
}

