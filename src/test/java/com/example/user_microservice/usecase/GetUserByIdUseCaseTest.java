package com.example.user_microservice.usecase;

import com.example.user_microservice.application.usecase.GetUserByIdUseCase;
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
class GetUserByIdUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetUserByIdUseCase getUserByIdUseCase;

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
    @DisplayName("Debería retornar un usuario cuando el ID existe")
    void shouldReturnUserWhenIdExists() {
        // Arrange
        UUID id = testUser.getId();
        when(userRepository.findById(id)).thenReturn(Optional.of(testUser));

        // Act
        User result = getUserByIdUseCase.execute(id);

        // Assert
        assertNotNull(result);
        assertEquals(testUser.getId(), result.getId());
        verify(userRepository).findById(id);
    }

    @Test
    @DisplayName("Debería retornar null cuando el ID no existe")
    void shouldReturnNullWhenIdDoesNotExist() {
        // Arrange
        UUID randomId = UUID.randomUUID();
        when(userRepository.findById(randomId)).thenReturn(Optional.empty());

        // Act
        User result = getUserByIdUseCase.execute(randomId);

        // Assert
        assertNull(result);
        verify(userRepository).findById(randomId);
    }


}
