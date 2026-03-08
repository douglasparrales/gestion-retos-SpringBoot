package com.gestion_retos.mapper;

import com.gestion_retos.dto.challenge.ChallengeRequestDTO;
import com.gestion_retos.dto.challenge.ChallengeResponseDTO;
import com.gestion_retos.model.Challenge;
import org.springframework.stereotype.Component;

@Component
public class ChallengeMapper {

    //Entity -> ResponseDTO.  When Out
    public static ChallengeResponseDTO toResponseDto(Challenge challenge){

        if (challenge == null){
            return null;
        }

        return ChallengeResponseDTO.builder()
                .challengeId(challenge.getChallengeId())
                .description(challenge.getDescription())
                .creator(challenge.getUser().getUserId())
                .points(challenge.getPoints())
                .title(challenge.getTitle())
                .endDate(challenge.getEndDate())
                .startDate(challenge.getStartDate())
                .build();
    }

    //RequestDTO -> Entity. When In
    public static Challenge toEntity(ChallengeRequestDTO dto){

        if (dto == null){
            return null;
        }

        return Challenge.builder()
                .description(dto.getDescription())
                .title(dto.getTitle())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .points(dto.getPoints())
                .build();
    }
}
