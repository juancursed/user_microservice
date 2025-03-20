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

    public User execute(UUID id) {
        //-----------AQUI VAN LAS EXCEPCIONES PARA VALIDAR---------------
        return userRepository.deleteUser(id);
    }
}
