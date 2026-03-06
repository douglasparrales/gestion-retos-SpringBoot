package com.gestion_retos.service;

import com.gestion_retos.dto.challenge.ChallengeResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeService implements IChallengeService{
    @Override
    public List<ChallengeResponseDTO> getAllChallenges() {
        return List.of();
    }

    @Override
    public ChallengeResponseDTO getChallengeByID(Long id) {
        return null;
    }

    @Override
    public ChallengeResponseDTO createChallenge(ChallengeResponseDTO challengeDto) {
        return null;
    }

    @Override
    public InscriptionResponseDTO createInscription(Long challengeID, Long userId, InscriptionResponseDTO inscriptionResponseDto) {
        return null;
    }

    @Override
    public InscriptionResponseDTO completeInscription(Long challengeID, Long userId, InscriptionResponseDTO inscriptionResponseDto) {
        return null;
    }

    @Override
    public ChallengeResponseDTO updateChallenge(Long id, ChallengeResponseDTO challengeDto) {
        return null;
    }

    @Override
    public void deleteChallenge(Long id) {

    }
}
