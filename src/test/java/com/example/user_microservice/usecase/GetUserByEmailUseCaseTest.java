package com.example.user_microservice.usecase;

import com.example.user_microservice.application.usecase.GetUserByEmailUseCase;
import com.example.user_microservice.domain.model.Role;
import com.example.user_microservice.domain.model.User;
import com.example.user_microservice.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetUserByEmailUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetUserByEmailUseCase getUserByEmailUseCase;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(UUID.randomUUID());
        testUser.setFirstName("John");
        testUser.setLastName("Doe");
        testUser.setEmail("john.doe@gmail.com");
        testUser.setPassword("password123");
        testUser.setBornDate(new Date(90, 0, 1)); // 1 de enero de 1990
        testUser.setPhone("+57313652345");
        testUser.setAccessLevel(1);
        testUser.setDepartment("Valle del Cauca");
        testUser.setCity("Buga");
        testUser.setAddress("Calle Principal 123");
        testUser.setPostalCode("12345");
        testUser.setRole(Role.CLIENT);
    }

    @Test
    @DisplayName("Debería retornar un usuario cuando el correo existe")
    void shouldReturnUserWhenEmailExists() {
        // Arrange
        when(userRepository.findByEmail(testUser.getEmail())).thenReturn(Optional.of(testUser));

        // Act
        User result = getUserByEmailUseCase.execute(testUser.getEmail());

        // Assert
        assertNotNull(result);
        assertEquals("john.doe@gmail.com", result.getEmail());
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        verify(userRepository, times(1)).findByEmail(testUser.getEmail());
    }

    @Test
    @DisplayName("Debería retornar null cuando el correo no existe")
    void shouldReturnNullWhenEmailDoesNotExist() {
        // Arrange
        String email = "no.exist@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act
        User result = getUserByEmailUseCase.execute(email);

        // Assert
        assertNull(result);
        verify(userRepository, times(1)).findByEmail(email);
    }



}
