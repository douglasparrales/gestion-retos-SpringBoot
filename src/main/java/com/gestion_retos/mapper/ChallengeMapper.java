package com.gestion_retos.mapper;

import com.gestion_retos.dto.challenge.ChallengeRequestDTO;
import com.gestion_retos.dto.challenge.ChallengeResponseDTO;
import com.gestion_retos.model.Challenge;
import org.springframework.stereotype.Component;

@Component
public class ChallengeMapper {

    //Entity -> ResponseDTO.  When Out
    public ChallengeResponseDTO toResponseDto(Challenge challenge){

        ChallengeResponseDTO dto = new ChallengeResponseDTO();
        dto.setChallengeId(challenge.getChallengeId());
        dto.setDescription(challenge.getDescription());
        dto.setCreator(challenge.getUser().getUserId());
        dto.setPoints(challenge.getPoints());
        dto.setTitle(challenge.getTitle());
        dto.setEndDate(challenge.getEndDate());
        dto.setStartDate(challenge.getStartDate());

        return dto;
    }

    //RequestDTO -> Entity. When In
    public Challenge toEntity(ChallengeRequestDTO dto){

        Challenge challenge = new Challenge();

        challenge.setDescription(dto.getDescription());
        challenge.setTitle(dto.getTitle());
        challenge.setStartDate(dto.getStartDate());
        challenge.setEndDate(dto.getEndDate());
        challenge.setPoints(dto.getPoints());

        return challenge;
    }
}
