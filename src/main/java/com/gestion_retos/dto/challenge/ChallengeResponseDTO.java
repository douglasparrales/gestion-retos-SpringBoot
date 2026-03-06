package com.gestion_retos.dto.challenge;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeResponseDTO {
    private Long challengeId;
    private String title;
    private String description;
    private Integer points;
    private LocalDate startDate;
    private LocalDate endDate;
    //User
    private Long creator;
}
