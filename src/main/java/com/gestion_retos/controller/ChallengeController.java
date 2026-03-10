package com.gestion_retos.controller;

import com.gestion_retos.dto.challenge.ChallengeRequestDTO;
import com.gestion_retos.dto.challenge.ChallengeResponseDTO;
import com.gestion_retos.service.ChallengeService;
import com.gestion_retos.service.InscriptionService;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<ChallengeResponseDTO> challengeResponseDTO = challengeService.getAllChallenges();
        return ResponseEntity.ok(challengeResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<@NonNull ChallengeResponseDTO> getById(@PathVariable Long id){
        ChallengeResponseDTO challengeResponseDTO = challengeService.getChallengeByID(id);
        return ResponseEntity.ok(challengeResponseDTO);
    }

    @PostMapping
    public ResponseEntity<@NonNull ChallengeResponseDTO> createChallenge(@RequestBody ChallengeRequestDTO challengeRequestDTO){
        ChallengeResponseDTO challengeResponseDTO = challengeService.createChallenge(challengeRequestDTO);
        return ResponseEntity.created(URI.create("/system/api/v1/challenge")).body(challengeResponseDTO);
    }

    @PostMapping("/{id}/inscription/{userId}")
    public ResponseEntity<?> createInscription(@PathVariable Long id, @PathVariable Long userId){
        inscriptionService.createInscription(id, userId);
        return ResponseEntity.created(URI.create("/system/api/v1/challenge/"+id+"/inscription/"+userId)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<@NonNull ChallengeResponseDTO> putChallenge(@PathVariable Long id, @RequestBody ChallengeRequestDTO challengeRequestDTO){
        ChallengeResponseDTO challengeResponseDTO = challengeService.updateChallenge(id, challengeRequestDTO);
        return ResponseEntity.ok(challengeResponseDTO);
    }

    @PutMapping("/{id}/complete/{userId}")
    public ResponseEntity<@NonNull ChallengeResponseDTO> completeChallenge(@PathVariable Long id, @PathVariable Long userId){
        inscriptionService.completeChallenge(id, userId);
        return ResponseEntity.ok().build()  ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<@NonNull ChallengeResponseDTO> deleteChallenge(@PathVariable Long id){
        challengeService.deleteChallenge(id);
        return ResponseEntity.noContent().build();
    }

}
