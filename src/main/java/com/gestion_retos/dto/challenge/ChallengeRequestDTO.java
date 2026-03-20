package com.gestion_retos.dto.challenge;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
    @NotBlank(message = "title is required")
    private String title;
    @NotNull(message = "description is required")
    private String description;
    @PositiveOrZero(message = "points must positive or zero")
    private Integer points;
    @FutureOrPresent(message = "start date must future or present")
    private LocalDate startDate;
    @FutureOrPresent(message = "end date must future or present")
    private LocalDate endDate;
    private Long creatorId;
}
