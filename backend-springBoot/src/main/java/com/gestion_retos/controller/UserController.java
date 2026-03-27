package com.gestion_retos.controller;

import com.gestion_retos.dto.user.UserRequestDTO;
import com.gestion_retos.dto.user.UserResponseDTO;
import com.gestion_retos.service.InscriptionService;
import com.gestion_retos.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<@NonNull List<UserResponseDTO>> getAllUsersByRanking(){
        List<UserResponseDTO> users = userService.getAllUsersByRanking();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<@NonNull UserResponseDTO> getById(@PathVariable Long id){
        UserResponseDTO userResponseDTO = userService.getUserById(id);
        return ResponseEntity.ok(userResponseDTO);
    }

    @PostMapping
    public ResponseEntity<@NonNull UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO userResponseDTO = userService.createUser(userRequestDTO);
        return ResponseEntity.created(URI.create("/system/api/v1/user/"+ userResponseDTO.getUserId())).body(userResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<@NonNull UserResponseDTO> updateUser(@PathVariable Long id,@Valid @RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO userResponseDTO = userService.updateUser(id, userRequestDTO);
        return ResponseEntity.ok(userResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<@NonNull Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}