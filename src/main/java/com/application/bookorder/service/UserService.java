package com.application.bookorder.service;

import com.application.bookorder.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO addNewUser(UserDTO userDTO);

    UserDTO getUserById(Long userId);

    UserDTO updateUserInfo(UserDTO userDTO);

    void deleteUserById(Long userId);
}
