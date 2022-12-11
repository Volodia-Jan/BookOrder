package com.application.bookorder.resource;

import com.application.bookorder.dto.UserDTO;
import com.application.bookorder.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import util.JacksonUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserResourceTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService userService;
    private UserDTO given;

    @BeforeEach
    void setUp() {
        given = new UserDTO(0L, "Test Name", "testEmail@gmail.com");
    }

    @AfterEach
    void tearDown() {
        given = null;
    }


    @Test
    void getAllUsers() throws Exception {
        given(userService.getAllUsers())
                .willReturn(List.of(given));
        mvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(given.getName())))
                .andExpect(jsonPath("$[0].email", is(given.getEmail())));
    }

    @Test
    void getUserById() throws Exception {
        given(userService.getUserById(given.getId()))
                .willReturn(given);
        mvc.perform(get(String.format("/api/v1/users/%d", given.getId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(given.getName())))
                .andExpect(jsonPath("$.email", is(given.getEmail())));
    }

    @Test
    void addNewUser() throws Exception {
        String jsonBody = JacksonUtil.serialize(given);
        assertThat(jsonBody).isNotNull();
        mvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(given.getName())))
                .andExpect(jsonPath("$.email", is(given.getEmail())));
    }

    @Test
    void updateUserInfo() throws Exception {
        given(userService.getUserById(given.getId()))
                .willReturn(given);
        given.setEmail("newEmail@gmail.com");
        String jsonBody = JacksonUtil.serialize(given);
        assertThat(jsonBody).isNotNull();
        mvc.perform(put("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(given.getName())))
                .andExpect(jsonPath("$.email", is(given.getEmail())));
    }

    @Test
    void deleteUserById() throws Exception {
        given(userService.getUserById(given.getId()))
                .willReturn(given);
        mvc.perform(delete(String.format("/api/v1/users/%d", given.getId())))
                .andExpect(status().isOk());
    }
}