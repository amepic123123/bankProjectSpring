package com.example.demo.dto;

import java.math.BigDecimal;

import com.example.demo.model.AccountType;

public record AccountResponse (

    Long id,
    Long accountNumber,
    AccountType accountType,
    BigDecimal balance

) {}
