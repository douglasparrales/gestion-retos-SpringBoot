package com.gestion_retos.dto.user;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long userId;
    private String username;
    private String email;
    private Integer totalPoints;
    private LocalDate registrationDate;
}
