package com.example.user_microservice;

import com.example.user_microservice.application.usecase.SaveUserUseCase;
import com.example.user_microservice.domain.model.Role;
import com.example.user_microservice.domain.model.User;
import com.example.user_microservice.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SaveUserUseCaseTest {

    private SaveUserUseCase saveUserUseCase;
    private InMemoryUserRepository userRepository;
    private SimplePasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserRepository();
        passwordEncoder = new SimplePasswordEncoder();
        saveUserUseCase = new SaveUserUseCase(userRepository, passwordEncoder);
    }

    private User getUser() {
        User user = new User();
        user.setFirstName("Manuel");
        user.setLastName("Vidales");
        user.setEmail("manuel@example.com");
        user.setPassword("password123");
        user.setBornDate(new Date(2000 - 1900, Calendar.JANUARY, 1)); // 2000-01-01
        user.setPhone("+573001112233");
        user.setDepartment("Valle del Cauca");
        user.setCity("Cali");
        user.setAddress("Calle 123");
        user.setPostalCode("760001");
        user.setAccessLevel(1);
        user.setRole(Role.CLIENT);
        return user;
    }

    @Test
    void shouldSaveUserWithEncryptedPassword() {
        User user = getUser();
        User savedUser = saveUserUseCase.execute(user);

        assertTrue(savedUser.getPassword().startsWith("encoded_"));
        assertEquals("encoded_password123", savedUser.getPassword());
        assertEquals(user, userRepository.savedUser); // Verifica que fue guardado
    }

    @Test
    void shouldThrowExceptionIfEmailExists() {
        User user = getUser();
        userRepository.save(user); // ya existe

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            saveUserUseCase.execute(user);
        });

        assertEquals("El correo ya está registrado", exception.getMessage());
    }

    // Implementación fake de UserRepository
    static class InMemoryUserRepository implements UserRepository {
        private final Map<String, User> usersByEmail = new HashMap<>();
        public User savedUser;

        @Override
        public Optional<User> findByEmail(String email) {
            return Optional.ofNullable(usersByEmail.get(email));
        }

        @Override
        public void deleteById(UUID id) {

        }

        @Override
        public User save(User user) {
            usersByEmail.put(user.getEmail(), user);
            this.savedUser = user;
            return user;
        }

        @Override
        public Optional<User> findById(UUID id) {
            return Optional.empty();
        }


    }

    // Implementación simple de PasswordEncoder
    static class SimplePasswordEncoder implements PasswordEncoder {
        @Override
        public String encode(CharSequence rawPassword) {
            return "encoded_" + rawPassword;
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            return encodedPassword.equals("encoded_" + rawPassword);
        }
    }
}
