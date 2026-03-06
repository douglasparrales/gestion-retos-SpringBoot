package com.gestion_retos.service;

import com.gestion_retos.dto.user.UserRequestDTO;
import com.gestion_retos.dto.user.UserResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public List<UserResponseDTO> getAllUsersByRanking() {
        return List.of();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        return null;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        return null;
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
