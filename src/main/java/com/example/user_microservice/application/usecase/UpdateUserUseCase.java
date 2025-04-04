package com.example.user_microservice.application.usecase;

import com.example.user_microservice.domain.repository.UserRepository;
import com.example.user_microservice.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserUseCase {
    private final UserRepository userRepository;

    public UpdateUserUseCase(UserRepository userRepository){this.userRepository = userRepository;}

    public User execute(User updatedData){
        User existingUser = userRepository.findById(updatedData.getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        if (updatedData.getFirstName() != null) {
            existingUser.setFirstName(updatedData.getFirstName());
        }
        if (updatedData.getLastName() != null) {
            existingUser.setLastName(updatedData.getLastName());
        }
        if (updatedData.getEmail() != null) {
            existingUser.setEmail(updatedData.getEmail());
        }
        if (updatedData.getPassword() != null) {
            existingUser.setPassword(updatedData.getPassword());
        }
        if (updatedData.getBornDate() != null) {
            existingUser.setBornDate(updatedData.getBornDate());
        }
        if (updatedData.getPhone() != null) {
            existingUser.setPhone(updatedData.getPhone());
        }
        if (updatedData.getLicense() != null) {
            existingUser.setLicense(updatedData.getLicense());
        }
        if (updatedData.getVehicle() != null) {
            existingUser.setVehicle(updatedData.getVehicle());
        }
        // Para tipos primitivos, considera usar objetos (Integer) o una convención (por ejemplo, si es 0 no actualizar)
        if (updatedData.getAccessLevel() != 0) {
            existingUser.setAccessLevel(updatedData.getAccessLevel());
        }
        if (updatedData.getDepartment() != null) {
            existingUser.setDepartment(updatedData.getDepartment());
        }
        if (updatedData.getCity() != null) {
            existingUser.setCity(updatedData.getCity());
        }
        if (updatedData.getAddress() != null) {
            existingUser.setAddress(updatedData.getAddress());
        }
        if (updatedData.getPostalCode() != null) {
            existingUser.setPostalCode(updatedData.getPostalCode());
        }
        if (updatedData.getRole() != null) {
            existingUser.setRole(updatedData.getRole());
        }

        return userRepository.save(existingUser);
    }

}
