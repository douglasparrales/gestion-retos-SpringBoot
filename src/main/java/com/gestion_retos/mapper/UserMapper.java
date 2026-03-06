package com.gestion_retos.mapper;

import com.gestion_retos.dto.user.UserRequestDTO;
import com.gestion_retos.dto.user.UserResponseDTO;
import com.gestion_retos.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    // Entity -> ResponseDTO
    public UserResponseDTO toResponseDto(User user){
        UserResponseDTO dto = new UserResponseDTO();

        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setTotalPoints(user.getTotalPoints());
        dto.setRegistrationDate(user.getRegistrationDate());

        return dto;
    }

    // RequestDTO -> Entity
    public User toEntity(UserRequestDTO dto){
        User user = new User();

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }
}
