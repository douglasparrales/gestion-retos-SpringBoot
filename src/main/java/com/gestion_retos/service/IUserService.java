package com.gestion_retos.service;

import com.gestion_retos.dto.user.UserResponseDTO;

import java.util.List;

public interface IUserService {
    //getAllUsersByRanking
    List<UserResponseDTO> getAllUsersByRanking();

    //getUserById
    UserResponseDTO getUserById(Long id);

    //createUser
    UserResponseDTO createUser(UserResponseDTO userResponseDto);

    //updateUser
    UserResponseDTO updateUser(Long id, UserResponseDTO userResponseDto);

    //deleteUser
    void deleteUser(Long id);
}
