package com.gestion_retos.service;

public interface InscriptionService {
    //createInscription
    void createInscription(Long challengeId, Long userId);

    //completeChallenge
    void completeChallenge(Long challengeId, Long userId);
}
