package com.example.user_microservice.application.usecase;

import com.example.user_microservice.domain.repository.UserRepository;
import com.example.user_microservice.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserUseCase {
    private final UserRepository userRepository;

    public UpdateUserUseCase(UserRepository userRepository){this.userRepository = userRepository;}

    public User execute(User user){
        userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));


        return userRepository.save(user);
    }

}
