package com.example.user_microservice.application.usecase;

import com.example.user_microservice.domain.model.Client;
import com.example.user_microservice.domain.model.User;
import com.example.user_microservice.domain.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class SaveClientUseCase {
    private final UserRepository userRepository;

    public SaveClientUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Client execute(Client client) {
        if(userRepository.findByEmail(client.getEmail()).isPresent()){
            throw new IllegalArgumentException("El correo ya está registrado carechimba");
        }

        return (Client) userRepository.save(client);
    }
}

