package com.application.bookorder.repository;

import com.application.bookorder.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private UserRepository underTest;
    private User given;

    @BeforeEach
    void setUp() {
        underTest = new UserRepository();
        given = new User(0L, "TestName", "testName@gmail.com");
    }

    @AfterEach
    void tearDown() {
        given = null;
    }

    @Test
    void itShouldSave() {
        User saved = underTest.save(given);
        assertThat(saved.getId()).isNotZero();
        assertThat(saved.getName()).isEqualTo(given.getName());
        assertThat(saved.getEmail()).isEqualTo(given.getEmail());
    }

    @Test
    void itShouldFindAll() {
        User saved = underTest.save(given);
        List<User> userList = underTest.findAll();
        assertThat(userList).isNotEmpty();
        assertThat(userList.contains(saved)).isTrue();
    }

    @Test
    void itShouldFindById() {
        User saved = underTest.save(given);
        assertThat(saved.getId()).isNotZero();
        User foundById = underTest.findById(saved.getId());
        assertThat(foundById).isEqualTo(saved);
    }

    @Test
    void itShouldThrowIllegalArgumentExceptionWhenFindById(){
        assertThatThrownBy(() -> underTest.findById(given.getId()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("Not found by id:%d", given.getId()));
    }

    @Test
    void itShouldDeleteById() {
        User saved = underTest.save(given);
        assertThat(saved.getId()).isNotZero();
        underTest.deleteById(saved.getId());
        List<User> allUsers = underTest.findAll();
        assertThat(allUsers.contains(saved)).isFalse();
    }

    @Test
    void itShouldUpdate() {
        User saved = underTest.save(given);
        assertThat(saved.getId()).isNotZero();
        saved.setName("NewTest Name");
        saved.setName("new email");
        User updated = underTest.update(saved);
        assertThat(updated).isEqualTo(saved);
    }
}