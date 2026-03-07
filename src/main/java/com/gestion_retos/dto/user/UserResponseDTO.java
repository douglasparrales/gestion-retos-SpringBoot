package com.gestion_retos.dto.user;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private Long userId;
    private String username;
    private String email;
    private Integer totalPoints;
    private LocalDate registrationDate;
}
