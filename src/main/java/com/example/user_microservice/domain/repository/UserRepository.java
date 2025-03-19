package com.example.user_microservice.domain.repository;


import com.example.user_microservice.domain.model.User;


import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(UUID id);
    Optional<User> findByEmail(String email);
    User deleteUser(UUID id);
}
