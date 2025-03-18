package com.example.user_microservice.application.usecase;

import com.example.user_microservice.domain.model.User;
import com.example.user_microservice.domain.repository.UserRepository;
import java.util.UUID;

public class DeleteUserUseCase {
    private final UserRepository userRepository;

    public DeleteUserUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void execute(UUID id) {
        //-----------AQUI VAN LAS EXCEPCIONES PARA VALIDAR---------------
        userRepository.deleteUser(id);
    }
}
