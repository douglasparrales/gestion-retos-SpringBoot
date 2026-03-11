package com.gestion_retos.service;

import com.gestion_retos.dto.challenge.ChallengeRequestDTO;
import com.gestion_retos.dto.challenge.ChallengeResponseDTO;
import com.gestion_retos.exception.ResourceNotFoundException;
import com.gestion_retos.exception.IllegalStateException;
import com.gestion_retos.mapper.ChallengeMapper;
import com.gestion_retos.model.Challenge;
import com.gestion_retos.model.User;
import com.gestion_retos.repository.ChallengeRepository;
import com.gestion_retos.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class ChallengeServiceImpl implements ChallengeService {

    private final ChallengeRepository repo;
    private final UserRepository userRepo;

    @Override
    public List<ChallengeResponseDTO> getAllChallenges() {
        //1. find all | 2. sort by end date before actual date | 3. to dto | 4. to list
        return repo.findByEndDateAfter(LocalDate.now())
                .stream().map(ChallengeMapper::toResponseDto)
                .toList();
    }

    @Override
    public ChallengeResponseDTO getChallengeByID(Long id) {
        //1. exist | 2. find by id | 3. to dto
        Challenge challenge = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id: "+id+" not exist"));
        return ChallengeMapper.toResponseDto(challenge);
    }

    @Override
    public ChallengeResponseDTO createChallenge(ChallengeRequestDTO challengeDto) {

        LocalDate today = LocalDate.now();
        LocalDate endDate= challengeDto.getEndDate();
        LocalDate startDate = challengeDto.getStartDate();

        if(startDate.isAfter(endDate)){
            throw  new IllegalStateException("Start date must be before end date");
        }

        if (endDate.isBefore(today)){
            throw new IllegalStateException("End date cannot be in the past");
        }

        if (startDate.isBefore(today)) {
            throw new IllegalStateException("Start date cannot be in the past");
        }

        User user = userRepo.findById(challengeDto.getCreatorId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Challenge challenge = ChallengeMapper.toEntity(challengeDto);
        challenge.setUser(user);
        Challenge savedChallenge = repo.save(challenge);
        return ChallengeMapper.toResponseDto(savedChallenge);
    }

    @Override
    public ChallengeResponseDTO updateChallenge(Long id, ChallengeRequestDTO challengeDto) {
        Challenge challenge = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id: "+id+" not exist"));

        User user = userRepo.findById(challengeDto.getCreatorId())
                        .orElseThrow(() -> new ResourceNotFoundException("creator not found"));

        challenge.setTitle(challengeDto.getTitle());
        challenge.setDescription(challengeDto.getDescription());
        challenge.setPoints(challengeDto.getPoints());
        challenge.setEndDate(challengeDto.getEndDate());
        challenge.setStartDate(challengeDto.getStartDate());
        challenge.setUser(user);

        Challenge savedChallenge = repo.save(challenge);
        return ChallengeMapper.toResponseDto(savedChallenge);
    }

    @Override
    public void deleteChallenge(Long id) {
        Challenge challenge = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id: "+id+" not exist"));
        repo.delete(challenge);
    }
}
