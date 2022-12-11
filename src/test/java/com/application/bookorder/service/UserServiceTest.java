package com.application.bookorder.service;

import com.application.bookorder.dto.UserDTO;
import com.application.bookorder.entity.User;
import com.application.bookorder.repository.UserRepository;
import com.application.bookorder.service.impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

class UserServiceTest {
    private UserServiceImpl underTest;
    private UserDTO given;

    @BeforeEach
    void setUp() {
        underTest = new UserServiceImpl(new UserRepository());
        given = new UserDTO(0L, "Test Name", "testEmail@gmail.com");
    }

    @AfterEach
    void tearDown() {
        given = null;
    }

    @Test
    void getAllUsers() {
        UserDTO saved = underTest.addNewUser(given);
        List<UserDTO> allUsers = underTest.getAllUsers();
        assertThat(allUsers).isNotEmpty();
        assertThat(allUsers.contains(saved)).isTrue();
    }

    @Test
    void addNewUser() {
        UserDTO saved = underTest.addNewUser(given);
        assertThat(saved.getId()).isNotZero();
        assertThat(saved.getName()).isEqualTo(given.getName());
        assertThat(saved.getEmail()).isEqualTo(given.getEmail());
    }

    @Test
    void getUserById() {
        UserDTO saved = underTest.addNewUser(given);
        UserDTO foundById = underTest.getUserById(saved.getId());
        assertThat(foundById).isEqualTo(saved);
    }

    @Test
    void updateUserInfo() {
        UserDTO saved = underTest.addNewUser(given);
        saved.setName("NewTest Name");
        saved.setName("new email");
        UserDTO updated = underTest.updateUserInfo(saved);
        assertThat(updated).isEqualTo(saved);
    }

    @Test
    void deleteUserById() {
        UserDTO saved = underTest.addNewUser(given);
        assertThat(saved.getId()).isNotZero();
        underTest.deleteUserById(saved.getId());
        List<UserDTO> allUsers = underTest.getAllUsers();
        assertThat(allUsers.contains(saved)).isFalse();
    }
}