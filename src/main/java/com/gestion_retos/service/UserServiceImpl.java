package com.gestion_retos.service;

import com.gestion_retos.dto.user.UserRequestDTO;
import com.gestion_retos.dto.user.UserResponseDTO;
import com.gestion_retos.mapper.UserMapper;
import com.gestion_retos.model.User;
import com.gestion_retos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;
    private UserMapper mapper;

    @Override
    public List<UserResponseDTO> getAllUsersByRanking() {
        //1. find all | 2.sort desc | 3. to dto | 4. to list
         return repo.findAll(Sort.by(Sort.Direction.DESC, "totalPoints"))
                 .stream()
                 .map(mapper::toResponseDto)
                 .toList();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        //1. find exist | 2. toDto | 3. or else return exception
        return repo.findById(id).map(mapper::toResponseDto)
                .orElseThrow(() -> new RuntimeException("User have been not found with id: " + id));
    }

    //EXCEPTIONS GLOBALS I SHOULD TO IMPLEMENTS

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        //1. request to entity | 2. save | 3. entity to response
        User user = mapper.toEntity(userRequestDTO);
        User savedUser = repo.save(user);
        return mapper.toResponseDto(savedUser);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
