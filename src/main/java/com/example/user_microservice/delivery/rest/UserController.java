package com.example.user_microservice.delivery.rest;

import com.example.user_microservice.application.usecase.*;
import com.example.user_microservice.application.usecase.DeleteUserUseCase;
import com.example.user_microservice.application.usecase.GetUserByEmailUseCase;
import com.example.user_microservice.application.usecase.GetUserByIdUseCase;
import com.example.user_microservice.application.usecase.SaveUserUseCase;
import com.example.user_microservice.infraestructure.clients.auth.AuthService;
import com.example.user_microservice.domain.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

    @RestController
    @RequestMapping("/users")
public class UserController {

    private final SaveUserUseCase saveUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final GetUserByEmailUseCase getUserByEmailUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final AuthService authService;

        public UserController(SaveUserUseCase saveUserUseCase,
                              GetUserByEmailUseCase getUserByEmailUseCase,
                              GetUserByIdUseCase getUserByIdUseCase,
                              DeleteUserUseCase deleteUserUseCase,
                              UpdateUserUseCase updateUserUseCase,
                              AuthService authService)
    {
        this.saveUserUseCase = saveUserUseCase;
        this.getUserByIdUseCase = getUserByIdUseCase;
        this.getUserByEmailUseCase = getUserByEmailUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.authService = authService;
    }

  @PostMapping
    public User createUser(@RequestBody User user){
        return saveUserUseCase.execute(user);
  }

  @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id){
        return getUserByIdUseCase.execute(id);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<?> updateUser(
          @PathVariable UUID id ,
          @RequestBody User user,
          @RequestHeader("Authorization") String token)
  {
        boolean isValidToken = authService.isValidToken(token);
        if (!isValidToken) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        else{
            user.setId(id);
            return ResponseEntity.ok(updateUserUseCase.execute(user));
        }

  }

  @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(
            @PathVariable UUID id,
            @RequestHeader("Authorization") String token)
  {
      boolean isValidToken = authService.isValidToken(token);
      if (!isValidToken) {
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
      }else {
          deleteUserUseCase.execute(id);
          return ResponseEntity.noContent().build();
      }
  }


}
