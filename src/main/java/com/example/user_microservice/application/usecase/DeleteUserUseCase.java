package com.example.user_microservice.application.usecase;

import com.example.user_microservice.domain.model.User;
import com.example.user_microservice.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteUserUseCase {
    private final UserRepository userRepository;

    public DeleteUserUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void execute(UUID id) {
        userRepository.deleteById(id);
        //return  userRepository.deleteById(id);
    }
}