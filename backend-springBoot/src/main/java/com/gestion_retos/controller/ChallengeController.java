package com.gestion_retos.controller;

import com.gestion_retos.dto.challenge.ChallengeRequestDTO;
import com.gestion_retos.dto.challenge.ChallengeResponseDTO;
import com.gestion_retos.dto.user.UserResponseDTO;
import com.gestion_retos.service.ChallengeService;
import com.gestion_retos.service.InscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/challenge")
@AllArgsConstructor
@Tag(name = "challenges", description = "controller for challenges")
public class ChallengeController {

    private final ChallengeService challengeService;
    private final InscriptionService inscriptionService;

    @GetMapping
    public ResponseEntity<List<ChallengeResponseDTO>> getAllChallenges(){
        List<ChallengeResponseDTO> challenges = challengeService.getAllChallenges();
        return ResponseEntity.ok(challenges);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChallengeResponseDTO> getById(@PathVariable Long id){
        ChallengeResponseDTO challengeResponseDTO = challengeService.getChallengeByID(id);
        return ResponseEntity.ok(challengeResponseDTO);
    }

    @PostMapping
    public ResponseEntity<ChallengeResponseDTO> createChallenge(@Valid @RequestBody ChallengeRequestDTO challengeRequestDTO){
        ChallengeResponseDTO challengeResponseDTO = challengeService.createChallenge(challengeRequestDTO);
        return ResponseEntity.created(URI.create("/system/api/v1/challenge/"+challengeResponseDTO.getChallengeId())).body(challengeResponseDTO);
    }

    @Operation(summary = "create enrollment", description = "return enrollment by user id")
    @PostMapping("/{challengeId}/inscription/{userId}")
    public ResponseEntity<Void> createInscription(@PathVariable Long challengeId, @PathVariable Long userId){
        inscriptionService.createInscription(challengeId, userId);
        return ResponseEntity.created(URI.create("/system/api/v1/challenge/"+challengeId+"/inscription/"+userId)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChallengeResponseDTO> putChallenge(@PathVariable Long id,@Valid @RequestBody ChallengeRequestDTO challengeRequestDTO){
        ChallengeResponseDTO challengeResponseDTO = challengeService.updateChallenge(id, challengeRequestDTO);
        return ResponseEntity.ok(challengeResponseDTO);
    }

    @Operation(summary = "complete challenge", description = "endpoint to mark challenge as complete")
    @PutMapping("/{challengeId}/complete/{userId}")
    public ResponseEntity<Void> completeChallenge(@PathVariable Long challengeId, @PathVariable Long userId){
        inscriptionService.completeChallenge(challengeId, userId);
        return ResponseEntity.ok().build()  ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChallenge(@PathVariable Long id){
        challengeService.deleteChallenge(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "users by challenge", description = "get all users enrollment in only one challenge")
    @GetMapping("/{challengeId}/user")
    public ResponseEntity<List<UserResponseDTO>> getUsersByChallenge(@PathVariable Long challengeId) {
        return ResponseEntity.ok(inscriptionService.getUsersByChallenge(challengeId));
    }
}