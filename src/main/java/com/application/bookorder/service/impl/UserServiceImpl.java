package com.application.bookorder.service.impl;

import com.application.bookorder.mapper.UserMapper;
import com.application.bookorder.dto.UserDTO;
import com.application.bookorder.entity.User;
import com.application.bookorder.repository.UserRepository;
import com.application.bookorder.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    @Override
    public UserDTO addNewUser(UserDTO userDTO) {
        User entity = UserMapper.toEntity(userDTO);
        return UserMapper.toDTO(userRepository.save(entity));
    }

    @Override
    public UserDTO getUserById(Long userId) {
        return UserMapper.toDTO(userRepository.findById(userId));
    }

    @Override
    public UserDTO updateUserInfo(UserDTO userDTO) {
        User entity = UserMapper.toEntity(userDTO);
        return UserMapper.toDTO(userRepository.update(entity));
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
