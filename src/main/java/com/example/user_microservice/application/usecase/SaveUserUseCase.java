package com.example.user_microservice.application.usecase;


import com.example.user_microservice.domain.model.User;
import com.example.user_microservice.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class SaveUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SaveUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder){

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User execute(User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new IllegalArgumentException("El correo ya está registrado");
        }

        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        return (User) userRepository.save(user);
    }
}

