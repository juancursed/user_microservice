package com.example.user_microservice.application.usecase;

import com.example.user_microservice.domain.model.Role;
import com.example.user_microservice.domain.model.User;
import com.example.user_microservice.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UpdateUserUseCase updateUserUseCase;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    private User existingUser;
    private User updatedUser;

    @BeforeEach
    void setUp() {
        UUID userId = UUID.randomUUID();

        existingUser = new User();
        existingUser.setId(userId);
        existingUser.setFirstName("John");
        existingUser.setLastName("Doe");
        existingUser.setEmail("john.doe@gmail.com");
        existingUser.setPassword("password123");
        existingUser.setBornDate(new Date(90, 0, 1));
        existingUser.setPhone("+57313652345");
        existingUser.setAccessLevel(1);
        existingUser.setDepartment("Valle del Cauca");
        existingUser.setCity("Buga");
        existingUser.setAddress("Calle Principal 123");
        existingUser.setPostalCode("12345");
        existingUser.setRole(Role.CLIENT);

        updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setFirstName("Johnny");
        updatedUser.setCity("Cali");
        updatedUser.setAccessLevel(2); // Será actualizado porque es diferente de 0
    }

    @Test
    @DisplayName("Debería actualizar campos existentes correctamente")
    void shouldUpdateFieldsSuccessfully() {
        // Arrange
        when(userRepository.findById(updatedUser.getId())).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        User result = updateUserUseCase.execute(updatedUser);

        // Assert
        assertNotNull(result);
        assertEquals("Johnny", result.getFirstName());
        assertEquals("Doe", result.getLastName()); // No cambió
        assertEquals("Cali", result.getCity());
        assertEquals(2, result.getAccessLevel());

        verify(userRepository).save(userCaptor.capture());
        User savedUser = userCaptor.getValue();
        assertEquals("Johnny", savedUser.getFirstName());
        assertEquals("Cali", savedUser.getCity());
    }

    @Test
    @DisplayName("Debería lanzar excepción si el usuario no existe")
    void shouldThrowExceptionIfUserNotFound() {
        // Arrange
        when(userRepository.findById(updatedUser.getId())).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> updateUserUseCase.execute(updatedUser)
        );

        assertEquals("Usuario no encontrado", exception.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debería ignorar campos nulos o no actualizables")
    void shouldIgnoreNullOrUnchangeableFields() {
        // Arrange
        updatedUser.setFirstName(null);
        updatedUser.setAccessLevel(0); // No se actualiza
        when(userRepository.findById(updatedUser.getId())).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        User result = updateUserUseCase.execute(updatedUser);

        // Assert
        assertEquals("John", result.getFirstName()); // No se cambió
        assertEquals(1, result.getAccessLevel());   // No se cambió
    }
}
