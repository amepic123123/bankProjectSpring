package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest (

    @NotBlank(message =  "Name is required")
    @Size(max = 100, message = "Name must be less than 100 characters")
    String userName,

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    String email,

    @NotBlank
    @Size(min = 6, message = "Password must be at least 6 characters long")
    String password

){}

