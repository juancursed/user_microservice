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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaveUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private SaveUserUseCase saveUserUseCase;

    @Captor
    private ArgumentCaptor<User> userCaptor;

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
    @DisplayName("Debería guardar un usuario correctamente cuando el correo no existe")
    void shouldSaveUserSuccessfully() {
        // Arrange
        String originalPassword = "password123";
        String encryptedPassword = "encrypted_password";
        when(userRepository.findByEmail(testUser.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(originalPassword)).thenReturn(encryptedPassword);
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        // Act
        User savedUser = saveUserUseCase.execute(testUser);

        // Assert
        assertNotNull(savedUser);
        assertEquals(encryptedPassword, testUser.getPassword());
        verify(userRepository).findByEmail(testUser.getEmail());
        verify(passwordEncoder).encode(originalPassword); // Corregido: verificar con originalPassword
        verify(userRepository).save(testUser);
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el correo ya existe")
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        // Arrange
        when(userRepository.findByEmail(testUser.getEmail())).thenReturn(Optional.of(new User()));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> saveUserUseCase.execute(testUser)
        );

        assertEquals("El correo ya está registrado", exception.getMessage());
        verify(userRepository).findByEmail(testUser.getEmail());
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    @DisplayName("Debería encriptar la contraseña antes de guardar")
    void shouldEncryptPasswordBeforeSaving() {
        // Arrange
        String originalPassword = "password123";
        String encryptedPassword = "encrypted_password";
        testUser.setPassword(originalPassword);

        when(userRepository.findByEmail(testUser.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(originalPassword)).thenReturn(encryptedPassword);
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        // Act
        saveUserUseCase.execute(testUser);

        // Assert
        assertEquals(encryptedPassword, testUser.getPassword());
        verify(passwordEncoder).encode(originalPassword);
    }

    @Test
    @DisplayName("Debería guardar con los datos correctos usando ArgumentCaptor")
    void shouldSaveWithCorrectDataUsingArgumentCaptor() {
        // Arrange
        String originalPassword = "password123";
        String encryptedPassword = "encrypted_password";
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(originalPassword)).thenReturn(encryptedPassword);
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        // Act
        saveUserUseCase.execute(testUser);

        // Assert
        verify(userRepository).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();

        assertEquals(testUser.getFirstName(), capturedUser.getFirstName());
        assertEquals(testUser.getLastName(), capturedUser.getLastName());
        assertEquals(testUser.getEmail(), capturedUser.getEmail());
        assertEquals(encryptedPassword, capturedUser.getPassword());
        assertEquals(testUser.getRole(), capturedUser.getRole());
    }

    @Test
    @DisplayName("Debería manejar caso nulo")
    void shouldHandleNullInput() {
        // Act & Assert
        assertThrows(
                NullPointerException.class,
                () -> saveUserUseCase.execute(null)
        );

        verify(userRepository, never()).findByEmail(anyString());
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any(User.class));
    }
}