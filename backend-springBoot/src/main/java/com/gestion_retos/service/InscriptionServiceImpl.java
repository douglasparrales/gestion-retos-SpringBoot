package com.gestion_retos.service;

import com.gestion_retos.dto.user.UserResponseDTO;
import com.gestion_retos.exception.ResourceNotFoundException;
import com.gestion_retos.model.Challenge;
import com.gestion_retos.model.Inscription;
import com.gestion_retos.model.User;
import com.gestion_retos.repository.ChallengeRepository;
import com.gestion_retos.repository.InscriptionRepository;
import com.gestion_retos.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class InscriptionServiceImpl implements InscriptionService{


    private final InscriptionRepository repo;
    private final UserRepository userRepo;
    private final ChallengeRepository challengeRepo;


    @Override
    public List<UserResponseDTO> getUsersByChallenge(Long challengeId) {
        // exist challenge
        Challenge challenge = challengeRepo.findById(challengeId)
                .orElseThrow(() -> new ResourceNotFoundException("challenge id " + challengeId + " not found"));

        // get enrollment
        List<Inscription> inscriptions = repo.findByChallenge_ChallengeId(challengeId);

        // mapper to dto
        return inscriptions.stream()
                .map(inscription -> {
                    User user = inscription.getUser();

                    UserResponseDTO dto = new UserResponseDTO();
                    dto.setUserId(user.getUserId());
                    dto.setUsername(user.getUsername());
                    dto.setEmail(user.getEmail());
                    dto.setTotalPoints(user.getTotalPoints());
                    dto.setRegistrationDate(user.getRegistrationDate());

                    return dto;
                })
                .toList();
    }

    @Override
    public void createInscription(Long challengeId, Long userId) {
        //1. exist challenge and user | 2. save
        Challenge challenge = challengeRepo.findById(challengeId)
                .orElseThrow(() -> new ResourceNotFoundException("challenge id "+challengeId+" not found"));
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user id "+userId+" not found"));

        if (repo.existsByUser_UserIdAndChallenge_ChallengeId(userId, challengeId)) {
            throw new IllegalStateException("User already registered in this challenge");
        }

        LocalDate today = LocalDate.now();

        if (challenge.getEndDate().isBefore(today)){
            throw new IllegalStateException("Challenge is already expired");
        }

        Inscription inscription = new Inscription();
        inscription.setInscriptionDate(today);
        inscription.setCompleted(false);
        inscription.setUser(user);
        inscription.setChallenge(challenge);

        repo.save(inscription);
    }

    @Override
    @Transactional
    public void completeChallenge(Long challengeId, Long userId) {
        Challenge challenge = challengeRepo.findById(challengeId)
                .orElseThrow(() -> new ResourceNotFoundException("challenge id "+challengeId+" not found"));
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user id "+userId+" not found"));

        Inscription inscription = repo.findByUser_UserIdAndChallenge_ChallengeId(userId, challengeId)
                .orElseThrow(() -> new IllegalStateException("User is not registered in this challenge"));

        if (inscription.isCompleted()){
            throw new IllegalStateException("Challenge already completed");
        }

        inscription.setCompleted(true);

        user.setTotalPoints(user.getTotalPoints() + challenge.getPoints());

        repo.save(inscription);
        userRepo.save(user);
    }
}
