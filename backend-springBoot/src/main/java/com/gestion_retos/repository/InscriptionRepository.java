package com.gestion_retos.repository;

import com.gestion_retos.model.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface InscriptionRepository extends JpaRepository<Inscription,Long> {
    boolean existsByUser_UserIdAndChallenge_ChallengeId(Long userId, Long challengeId);
    Optional<Inscription> findByUser_UserIdAndChallenge_ChallengeId(Long userId, Long challengeId);
    List<Inscription> findByChallenge_ChallengeId(Long challengeId);
}
