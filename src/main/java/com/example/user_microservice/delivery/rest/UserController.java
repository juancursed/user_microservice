package com.example.user_microservice.delivery.rest;

import com.example.user_microservice.application.usecase.DeleteUserUseCase;
import com.example.user_microservice.application.usecase.GetUserByEmailUseCase;
import com.example.user_microservice.application.usecase.GetUserByIdUseCase;
import com.example.user_microservice.application.usecase.SaveUserUseCase;
import com.example.user_microservice.domain.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final SaveUserUseCase saveUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final GetUserByEmailUseCase getUserByEmailUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private PathVariable string;

    public UserController(SaveUserUseCase saveUserUseCase, GetUserByEmailUseCase getUserByEmailUseCase, GetUserByIdUseCase getUserByIdUseCase, DeleteUserUseCase deleteUserUseCase)
    {
        this.saveUserUseCase = saveUserUseCase;
        this.getUserByIdUseCase = getUserByIdUseCase;
        this.getUserByEmailUseCase = getUserByEmailUseCase;
        this.deleteUserUseCase = deleteUserUseCase;

    }

  @PostMapping
    public User createUser(@RequestBody User user){
        return saveUserUseCase.execute(user);
  }

  @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id){
        return getUserByIdUseCase.execute(id);
  }


  @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id){
      deleteUserUseCase.execute(id);
  }


}
