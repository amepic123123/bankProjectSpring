package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;
import com.example.demo.dto.*;
import com.example.demo.model.User;
import org.springframework.security.crypto.password.PasswordEncoder; 

@Service
public class UserService {

   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
   private final JwtService jwtService;

   public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
       this.userRepository = userRepository;
       this.passwordEncoder = passwordEncoder;
       this.jwtService = jwtService;
   }

   private boolean accountExists(String email) {
       return userRepository.existsByEmail(email);
   }

  public UserResponse createUser(CreateUserRequest request){

    if (accountExists(request.email())) throw new IllegalArgumentException("User with this email already exists");
        User user = new User();
        user.setName(request.userName());
        user.setEmail(request.email());
        user.setPasswordHash(passwordEncoder.encode(request.password()));

        User savedUser = userRepository.save(user);
        return new UserResponse(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }
    public LoginResponse loginUser(LoginRequest request){
        User user = authenticate(request.email(), request.password());
        String token = jwtService.generateToken(user);
        return new LoginResponse(token);
    }
    private User authenticate(String email, String password){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!passwordEncoder.matches(password, user.getPasswordHash()))
            throw new IllegalArgumentException("Invalid email or password");

        return user;
    }

}
