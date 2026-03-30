package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.dto.LoginRequest;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid@RequestBody CreateUserRequest request){

        UserResponse response = userService.createUser(request);
        return ResponseEntity.ok(response);
    
    }
     @Validated
     @PostMapping("/login")
    public ResponseEntity<?> LoginUser(@Valid@RequestBody LoginRequest request){
      
            LoginResponse response = userService.loginUser(request);
            return ResponseEntity.ok(response);

        }
        
        
    }
    
   
