package com.example.demo.dto;

import java.math.BigDecimal;

import com.example.demo.TransactionType;

public record TransactionResponse (
    Long id,
    Long accountId,
    TransactionType transactionType,
    BigDecimal amount,
    Long sourceAccountId,
    String status,
    String createdAt
    
) {}