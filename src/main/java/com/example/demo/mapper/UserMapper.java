package com.example.demo.mapper;

import org.mapstruct.Mapper;
import com.example.demo.dto.UserResponse;
import com.example.demo.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    UserResponse toResponse(User user);
    
}
