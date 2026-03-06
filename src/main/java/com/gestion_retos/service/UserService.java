package com.gestion_retos.service;

import com.gestion_retos.dto.user.UserRequestDTO;
import com.gestion_retos.dto.user.UserResponseDTO;

import java.util.List;

public interface UserService {
    //getAllUsersByRanking
    List<UserResponseDTO> getAllUsersByRanking();

    //getUserById
    UserResponseDTO getUserById(Long id);

    //createUser
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);

    //updateUser
    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);

    //deleteUser
    void deleteUser(Long id);
}
