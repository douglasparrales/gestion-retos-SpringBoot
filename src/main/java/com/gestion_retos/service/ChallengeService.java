package com.gestion_retos.service;

import com.gestion_retos.dto.challenge.ChallengeRequestDTO;
import com.gestion_retos.dto.challenge.ChallengeResponseDTO;

import java.util.List;

public interface ChallengeService {
    //getAll
    List<ChallengeResponseDTO> getAllChallenges();

    //getChallengeByID
    ChallengeResponseDTO getChallengeByID(Long id);

    //create
    ChallengeResponseDTO createChallenge(ChallengeRequestDTO challengeDto);

    //createInscription
    void createInscription(Long challengeId, Long userId);

    //completeChallenge
    void completeChallenge(Long challengeId, Long userId);

    //update
    ChallengeResponseDTO updateChallenge(Long id, ChallengeRequestDTO challengeDto);

    //delete
    void deleteChallenge(Long id);
}
