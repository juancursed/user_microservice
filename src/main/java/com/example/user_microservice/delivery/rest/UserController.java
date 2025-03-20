package com.example.user_microservice.delivery.rest;

import com.example.user_microservice.application.usecase.DeleteUserUseCase;
import com.example.user_microservice.application.usecase.GetUserByEmailUseCase;
import com.example.user_microservice.application.usecase.GetUserByIdUseCase;
import com.example.user_microservice.application.usecase.SaveClientUseCase;
import com.example.user_microservice.domain.model.Client;
import com.example.user_microservice.domain.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final SaveClientUseCase saveUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final GetUserByEmailUseCase getUserByEmailUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private PathVariable string;

    public UserController(SaveClientUseCase saveUserUseCase, GetUserByEmailUseCase getUserByEmailUseCase, GetUserByIdUseCase getUserByIdUseCase, DeleteUserUseCase deleteUserUseCase)
    {
        this.saveUserUseCase = saveUserUseCase;
        this.getUserByIdUseCase = getUserByIdUseCase;
        this.getUserByEmailUseCase = getUserByEmailUseCase;
        this.deleteUserUseCase = deleteUserUseCase;

    }

  @PostMapping
    public Client createUser(@RequestBody Client client){
        return saveUserUseCase.execute(client);
  }

 /* @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id){
        return getUserByIdUseCase.execute(id);
  }

  @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id){
      deleteUserUseCase.execute(id);
  }*/


}
