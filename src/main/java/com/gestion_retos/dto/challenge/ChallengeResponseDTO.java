package com.gestion_retos.dto.challenge;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
