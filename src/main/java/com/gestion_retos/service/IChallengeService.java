package com.gestion_retos.service;

import com.gestion_retos.dto.challenge.ChallengeResponseDTO;

import java.util.List;

public interface IChallengeService {
    //getAll
    List<ChallengeResponseDTO> getAllChallenges();

    //getChallengeByID
    ChallengeResponseDTO getChallengeByID(Long id);

    //create
    ChallengeResponseDTO createChallenge(ChallengeResponseDTO challengeDto);

    //createInscription
    InscriptionResponseDTO createInscription(Long challengeID, Long userId, InscriptionResponseDTO inscriptionResponseDto);

    //completeChallenge
    InscriptionResponseDTO completeInscription(Long challengeID, Long userId, InscriptionResponseDTO inscriptionResponseDto);

    //update
    ChallengeResponseDTO updateChallenge(Long id, ChallengeResponseDTO challengeDto);

    //delete
    void deleteChallenge(Long id);
}
