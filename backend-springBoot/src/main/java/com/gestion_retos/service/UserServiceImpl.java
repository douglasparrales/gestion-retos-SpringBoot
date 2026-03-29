package com.gestion_retos.service;

import com.gestion_retos.dto.user.UserRequestDTO;
import com.gestion_retos.dto.user.UserResponseDTO;
import com.gestion_retos.exception.BusinessDataIntegrityException;
import com.gestion_retos.exception.ResourceNotFoundException;
import com.gestion_retos.mapper.UserMapper;
import com.gestion_retos.model.User;
import com.gestion_retos.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    @Override
    public List<UserResponseDTO> getAllUsersByRanking() {
        //1. find all | 2.sort desc | 3. to dto | 4. to list
         return repo.findAll(Sort.by(Sort.Direction.DESC, "totalPoints"))
                 .stream()
                 .map(UserMapper::toResponseDto)
                 .toList();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        //1. find exist | 2. toDto | 3. or else return exception
        return repo.findById(id).map(UserMapper::toResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("User have been not found with id: " + id));
    }


    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        //1. request to entity | 2. save | 3. entity to response
        User user = UserMapper.toEntity(userRequestDTO);
        User savedUser = repo.save(user);
        return UserMapper.toResponseDto(savedUser);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        //1. find exist id | 2. update and save | 3. entity to response dto
         User user = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user with id " + id + " not found"));

         user.setUsername(userRequestDTO.getUsername());
         user.setEmail(userRequestDTO.getEmail());
         user.setPassword(userRequestDTO.getPassword());

         User updateUser = repo.save(user);

         return UserMapper.toResponseDto(updateUser);
    }

    @Override
    public void deleteUser(Long id) {
        //1 find exist | 2. delete
        User user = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user with id " + id + " not found"));

        try {
        repo.delete(user);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessDataIntegrityException("user have relations with other tables");
        }
    }
}