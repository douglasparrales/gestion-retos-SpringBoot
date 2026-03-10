package com.gestion_retos.repository;

import com.gestion_retos.dto.challenge.ChallengeResponseDTO;
import com.gestion_retos.model.Challenge;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ChallengeRepository extends JpaRepository<@NonNull Challenge,@NonNull Long>{
    List<Challenge> findByEndDateAfter(LocalDate date);
}
