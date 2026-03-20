package com.gestion_retos.controller;

import com.gestion_retos.dto.challenge.ChallengeRequestDTO;
import com.gestion_retos.dto.challenge.ChallengeResponseDTO;
import com.gestion_retos.service.ChallengeService;
import com.gestion_retos.service.InscriptionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/challenge")
@AllArgsConstructor
public class ChallengeController {

    private final ChallengeService challengeService;
    private final InscriptionService inscriptionService;

    @GetMapping
    public ResponseEntity<@NonNull List<ChallengeResponseDTO>> getAllChallenges(){
        List<ChallengeResponseDTO> challenges = challengeService.getAllChallenges();
        return ResponseEntity.ok(challenges);
    }

    @GetMapping("/{id}")
    public ResponseEntity<@NonNull ChallengeResponseDTO> getById(@PathVariable Long id){
        ChallengeResponseDTO challengeResponseDTO = challengeService.getChallengeByID(id);
        return ResponseEntity.ok(challengeResponseDTO);
    }

    @PostMapping
    public ResponseEntity<@NonNull ChallengeResponseDTO> createChallenge(@Valid @RequestBody ChallengeRequestDTO challengeRequestDTO){
        ChallengeResponseDTO challengeResponseDTO = challengeService.createChallenge(challengeRequestDTO);
        return ResponseEntity.created(URI.create("/system/api/v1/challenge/"+challengeResponseDTO.getChallengeId())).body(challengeResponseDTO);
    }

    @PostMapping("/{challengeId}/inscription/{userId}")
    public ResponseEntity<@NonNull Void> createInscription(@PathVariable Long challengeId, @PathVariable Long userId){
        inscriptionService.createInscription(challengeId, userId);
        return ResponseEntity.created(URI.create("/system/api/v1/challenge/"+challengeId+"/inscription/"+userId)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<@NonNull ChallengeResponseDTO> putChallenge(@PathVariable Long id,@Valid @RequestBody ChallengeRequestDTO challengeRequestDTO){
        ChallengeResponseDTO challengeResponseDTO = challengeService.updateChallenge(id, challengeRequestDTO);
        return ResponseEntity.ok(challengeResponseDTO);
    }

    @PutMapping("/{challengeId}/complete/{userId}")
    public ResponseEntity<@NonNull Void> completeChallenge(@PathVariable Long challengeId, @PathVariable Long userId){
        inscriptionService.completeChallenge(challengeId, userId);
        return ResponseEntity.ok().build()  ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<@NonNull Void> deleteChallenge(@PathVariable Long id){
        challengeService.deleteChallenge(id);
        return ResponseEntity.noContent().build();
    }
}