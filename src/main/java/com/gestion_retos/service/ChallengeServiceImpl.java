package com.gestion_retos.service;

import com.gestion_retos.dto.challenge.ChallengeRequestDTO;
import com.gestion_retos.dto.challenge.ChallengeResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    @Override
    public List<ChallengeResponseDTO> getAllChallenges() {
        return List.of();
    }

    @Override
    public ChallengeResponseDTO getChallengeByID(Long id) {
        return null;
    }

    @Override
    public ChallengeResponseDTO createChallenge(ChallengeRequestDTO challengeDto) {
        return null;
    }

    @Override
    public void createInscription(Long challengeId, Long userId) {

    }

    @Override
    public void completeChallenge(Long challengeId, Long userId) {

    }

    @Override
    public ChallengeResponseDTO updateChallenge(Long id, ChallengeRequestDTO challengeDto) {
        return null;
    }

    @Override
    public void deleteChallenge(Long id) {

    }
}
