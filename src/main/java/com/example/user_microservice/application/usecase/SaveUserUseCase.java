package com.example.user_microservice.application.usecase;


import com.example.user_microservice.domain.model.User;
import com.example.user_microservice.domain.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class SaveUserUseCase {
    private final UserRepository userRepository;

    public SaveUserUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User execute(User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new IllegalArgumentException("El correo ya está registrado carechimba");
        }

        return (User) userRepository.save(user);
    }
}

