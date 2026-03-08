package com.gestion_retos.mapper;

import com.gestion_retos.dto.user.UserRequestDTO;
import com.gestion_retos.dto.user.UserResponseDTO;
import com.gestion_retos.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserMapper {

    // Entity -> ResponseDTO
    public static UserResponseDTO toResponseDto(User user){

        if (user == null) {
            return  null;
        }

         return UserResponseDTO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .totalPoints(user.getTotalPoints())
                .registrationDate(user.getRegistrationDate())
                .build();
    }

    // RequestDTO -> Entity
    public static User toEntity(UserRequestDTO dto){

        if (dto == null){
            return null;
        }

         return User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                 .totalPoints(0)
                 .registrationDate(LocalDate.now())
                .build();
    }
}