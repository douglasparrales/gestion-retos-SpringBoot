package com.gestion_retos.service;

import com.gestion_retos.dto.ChallengeDTO;
import com.gestion_retos.dto.InscriptionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeService implements IChallengeService{
    @Override
    public List<ChallengeDTO> getAllChallenges() {
        return List.of();
    }

    @Override
    public ChallengeDTO getChallengeByID(Long id) {
        return null;
    }

    @Override
    public ChallengeDTO createChallenge(ChallengeDTO challengeDto) {
        return null;
    }

    @Override
    public InscriptionDTO createInscription(Long challengeID, Long userId, InscriptionDTO inscriptionDto) {
        return null;
    }

    @Override
    public InscriptionDTO completeInscription(Long challengeID, Long userId, InscriptionDTO inscriptionDto) {
        return null;
    }

    @Override
    public ChallengeDTO updateChallenge(Long id, ChallengeDTO challengeDto) {
        return null;
    }

    @Override
    public void deleteChallenge(Long id) {

    }
}
