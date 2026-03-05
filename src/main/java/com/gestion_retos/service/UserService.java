package com.gestion_retos.service;

import com.gestion_retos.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{
    @Override
    public List<UserDTO> getAllUsersByRanking() {
        return List.of();
    }

    @Override
    public UserDTO getUserById(Long id) {
        return null;
    }

    @Override
    public UserDTO createUser(UserDTO userDto) {
        return null;
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDto) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
