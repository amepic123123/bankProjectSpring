package com.example.demo.mapper;

import org.mapstruct.Mapper;
import com.example.demo.dto.AccountResponse;
import com.example.demo.model.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountResponse toResponse(Account account);
    
}
