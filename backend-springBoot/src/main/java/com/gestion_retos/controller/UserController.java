package com.gestion_retos.controller;

import com.gestion_retos.dto.user.UserRequestDTO;
import com.gestion_retos.dto.user.UserResponseDTO;
import com.gestion_retos.service.UserService;
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

@RestController
@AllArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@Tag(name = "Users", description = "Endpoints for managing users and rankings")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Get all users ordered by ranking",
            description = "Retrieve all users sorted by their ranking (e.g., points or score)"
    )
    @ApiResponse(responseCode = "200", description = "Users retrieved successfully")
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsersByRanking(){
        return ResponseEntity.ok(userService.getAllUsersByRanking());
    }

    @Operation(
            summary = "Get user by ID",
            description = "Retrieve a specific user by their unique ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(
            @Parameter(description = "User ID", example = "1")
            @PathVariable Long id){

        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(
            summary = "Create a new user",
            description = "Create a new user with the provided information"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(
            @Valid @RequestBody UserRequestDTO userRequestDTO){

        UserResponseDTO response = userService.createUser(userRequestDTO);

        return ResponseEntity
                .created(URI.create("/system/api/v1/user/" + response.getUserId()))
                .body(response);
    }

    @Operation(
            summary = "Update a user",
            description = "Update an existing user by their ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @Parameter(description = "User ID", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDTO userRequestDTO){

        return ResponseEntity.ok(
                userService.updateUser(id, userRequestDTO)
        );
    }

    @Operation(
            summary = "Delete a user",
            description = "Soft delete a user by their ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "User ID", example = "1")
            @PathVariable Long id){

        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}