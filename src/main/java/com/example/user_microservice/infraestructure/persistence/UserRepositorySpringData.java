package com.example.user_microservice.infraestructure.persistence;


import com.example.user_microservice.domain.model.User;
import com.example.user_microservice.domain.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepositorySpringData extends JpaRepository<User, UUID>, UserRepository {
    @Override
    Optional<User> findByEmail(String email);
}
