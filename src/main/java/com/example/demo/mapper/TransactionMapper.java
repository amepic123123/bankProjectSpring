package com.example.demo.mapper;

import org.mapstruct.Mapper;
import com.example.demo.model.Transaction;
import com.example.demo.dto.TransactionResponse;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    
    TransactionResponse toResponse(Transaction transaction);
    
}
