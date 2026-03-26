package com.example.demo.dto;

import java.math.BigDecimal;
import com.example.demo.TransactionType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TransactionRequest (


    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be a positive number")
    BigDecimal amount,

    @NotNull(message = "Transaction type is required")
    TransactionType type,

    @Min(value = 1, message = "Source account ID must be a positive number")
    @NotNull(message = "Source account ID is required")
    Long sourceAccountId,


    //No notNull cuz it could be optional for DEPOSIT and WITHDRAWAL
    @Min(value = 1, message = "Destination account ID must be a positive number")
    Long destinationAccountId

     
){}
