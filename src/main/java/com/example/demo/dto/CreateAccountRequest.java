package com.example.demo.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import com.example.demo.AccountType;


public record CreateAccountRequest(

    @NotNull(message = "Account number is required")
    @Min(value = 1000000000L, message = "Account number is too short")
    Long accountNumber,
    
    @NotNull(message = "Account type is required")
    AccountType accountType,

    @NotNull(message = "Initial deposit is required")
    @Positive(message = "Initial deposit must be a positive number")
    BigDecimal initialDeposit



){}


