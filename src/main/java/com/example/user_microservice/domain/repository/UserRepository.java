package com.example.user_microservice.domain.repository;


import com.example.user_microservice.domain.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}
