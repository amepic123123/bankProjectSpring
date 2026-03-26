package com.example.demo.dto;

import java.math.BigDecimal;
import com.example.demo.AccountType;

public record AccountResponse (

    Long id,
    String accountNumber,
    AccountType accountType,
    Long userId,
    BigDecimal balance

) {}
