package com.example.demo.dto;

import java.math.BigDecimal;
import com.example.demo.model.Account;
import com.example.demo.TransactionType;

public record TransactionResponse (
    
    Long id,
    Account fromAccount,
    TransactionType type,
    BigDecimal amount,
    Account toAccount,
    String createdAt
    
) {}