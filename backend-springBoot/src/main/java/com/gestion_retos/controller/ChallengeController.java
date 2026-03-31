package com.gestion_retos.controller;

import com.gestion_retos.dto.challenge.ChallengeRequestDTO;
import com.gestion_retos.dto.challenge.ChallengeResponseDTO;
import com.gestion_retos.dto.user.UserResponseDTO;
import com.gestion_retos.service.ChallengeService;
import com.gestion_retos.service.InscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.media.*;
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
@Tag(name = "Challenges", description = "Endpoints for managing challenges and user enrollments")
public class ChallengeController {

    private final ChallengeService challengeService;
    private final InscriptionService inscriptionService;

    @Operation(
            summary = "Get all challenges",
            description = "Retrieve a list of all active challenges"
    )
    @ApiResponse(responseCode = "200", description = "Successfully retrieved challenges")
    @GetMapping
    public ResponseEntity<List<ChallengeResponseDTO>> getAllChallenges(){
        return ResponseEntity.ok(challengeService.getAllChallenges());
    }

    @Operation(
            summary = "Get challenge by ID",
            description = "Retrieve a specific challenge using its unique ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Challenge found",
                    content = @Content(schema = @Schema(implementation = ChallengeResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Challenge not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ChallengeResponseDTO> getById(
            @Parameter(description = "ID of the challenge", example = "1")
            @PathVariable Long id){
        return ResponseEntity.ok(challengeService.getChallengeByID(id));
    }

    @Operation(
            summary = "Create a new challenge",
            description = "Create a new challenge. The end date is optional."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Challenge successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<ChallengeResponseDTO> createChallenge(
            @Valid @RequestBody ChallengeRequestDTO challengeRequestDTO){

        ChallengeResponseDTO response = challengeService.createChallenge(challengeRequestDTO);

        return ResponseEntity
                .created(URI.create("/system/api/v1/challenge/" + response.getChallengeId()))
                .body(response);
    }

    @Operation(
            summary = "Enroll a user in a challenge",
            description = "Creates an enrollment for a user in a specific challenge"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Enrollment created"),
            @ApiResponse(responseCode = "404", description = "User or challenge not found")
    })
    @PostMapping("/{challengeId}/inscription/{userId}")
    public ResponseEntity<Void> createInscription(
            @Parameter(description = "Challenge ID", example = "1")
            @PathVariable Long challengeId,
            @Parameter(description = "User ID", example = "10")
            @PathVariable Long userId){

        inscriptionService.createInscription(challengeId, userId);

        return ResponseEntity
                .created(URI.create("/system/api/v1/challenge/" + challengeId + "/inscription/" + userId))
                .build();
    }

    @Operation(
            summary = "Update a challenge",
            description = "Update an existing challenge by its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Challenge updated"),
            @ApiResponse(responseCode = "404", description = "Challenge not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ChallengeResponseDTO> putChallenge(
            @Parameter(description = "Challenge ID", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody ChallengeRequestDTO challengeRequestDTO){

        return ResponseEntity.ok(
                challengeService.updateChallenge(id, challengeRequestDTO)
        );
    }

    @Operation(
            summary = "Complete a challenge",
            description = "Marks a challenge as completed for a specific user"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Challenge marked as completed"),
            @ApiResponse(responseCode = "404", description = "User or challenge not found")
    })
    @PutMapping("/{challengeId}/complete/{userId}")
    public ResponseEntity<Void> completeChallenge(
            @Parameter(description = "Challenge ID", example = "1")
            @PathVariable Long challengeId,
            @Parameter(description = "User ID", example = "10")
            @PathVariable Long userId){

        inscriptionService.completeChallenge(challengeId, userId);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Delete a challenge",
            description = "Soft delete a challenge by its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Challenge deleted"),
            @ApiResponse(responseCode = "404", description = "Challenge not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChallenge(
            @Parameter(description = "Challenge ID", example = "1")
            @PathVariable Long id){

        challengeService.deleteChallenge(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Get users by challenge",
            description = "Retrieve all users enrolled in a specific challenge"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Challenge not found")
    })
    @GetMapping("/{challengeId}/user")
    public ResponseEntity<List<UserResponseDTO>> getUsersByChallenge(
            @Parameter(description = "Challenge ID", example = "1")
            @PathVariable Long challengeId) {

        return ResponseEntity.ok(
                inscriptionService.getUsersByChallenge(challengeId)
        );
    }
}