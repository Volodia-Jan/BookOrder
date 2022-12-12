package com.application.bookorder.mapper;

import com.application.bookorder.dto.UserDTO;
import com.application.bookorder.entity.User;

public class UserMapper {

    public static User toEntity(UserDTO dto){
        User entity = new User();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        return entity;
    }

    public static UserDTO toDTO(User entity){
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        return dto;
    }
}
