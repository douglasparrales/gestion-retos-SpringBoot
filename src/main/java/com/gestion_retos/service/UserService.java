package com.gestion_retos.service;

import com.gestion_retos.dto.user.UserResponseDTO;
import com.gestion_retos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    UserRepository repository;

    @Override
    public List<UserResponseDTO> getAllUsersByRanking() {
        return repository.findAll();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        return null;
    }

    @Override
    public UserResponseDTO createUser(UserResponseDTO userResponseDto) {
        return null;
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserResponseDTO userResponseDto) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
