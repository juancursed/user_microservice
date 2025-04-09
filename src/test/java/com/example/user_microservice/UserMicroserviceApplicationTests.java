package com.example.user_microservice;

import com.example.user_microservice.application.usecase.SaveUserUseCase;
import com.example.user_microservice.domain.model.User;
import com.example.user_microservice.domain.model.Role;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserMicroserviceApplicationTests {

    @Autowired
    private SaveUserUseCase saveUserUseCase;

    @Test
    @Rollback
    void testCrearUsuario() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        User nuevo = new User();
        nuevo.setFirstName("Juan");
        nuevo.setLastName("Trin");
        nuevo.setEmail("juantrin"+ UUID.randomUUID().toString() +"@gmail.com");
        nuevo.setPassword("Wosito123"); // >= 8 caracteres
        nuevo.setBornDate(sdf.parse("1990-01-01"));
        nuevo.setPhone("+1234567890");
        nuevo.setAccessLevel(1);
        nuevo.setDepartment("Valle del Cauca");
        nuevo.setCity("Tuluá york");
        nuevo.setAddress("Calle Falsa 123");
        nuevo.setPostalCode("12345");
        nuevo.setRole(Role.CLIENT);

        //----------Guardamos el Usuario test-----------
        User creado = saveUserUseCase.execute(nuevo);
        assertNotNull(creado);
        assertNotNull(creado.getId());
        assertEquals("Juan", creado.getFirstName());
        assertEquals(Role.CLIENT, creado.getRole());
    }

}
