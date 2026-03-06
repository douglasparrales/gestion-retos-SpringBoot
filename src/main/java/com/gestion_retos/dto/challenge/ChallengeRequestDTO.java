package com.gestion_retos.dto.challenge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeRequestDTO {
    private String title;
    private String description;
    private Integer points;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long creatorId;
}
