package com.application.bookorder.resource;

import com.application.bookorder.dto.HotelDTO;
import com.application.bookorder.dto.RoomDTO;
import com.application.bookorder.entity.enumeration.RoomType;
import com.application.bookorder.service.HotelService;
import com.application.bookorder.util.JacksonUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HotelResource.class)
class HotelResourceTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private HotelService hotelService;
    private HotelDTO given;

    @BeforeEach
    void setUp() {
        given = new HotelDTO();
        given.setId(0L);
        given.setName("Sultan");
        given.setRooms(new ArrayList<>(List.of(
                new RoomDTO(205, RoomType.STANDART, false, 2000),
                new RoomDTO(205, RoomType.STUDIO, true, 4000),
                new RoomDTO(205, RoomType.SUPERIOR, false, 3050),
                new RoomDTO(205, RoomType.CORNER_ROOM, true, 5000)
        )));
    }

    @AfterEach
    void tearDown() {
        given = null;
    }

    @Test
    void getAllHotels() throws Exception {
        given(hotelService.getAllHotels())
                .willReturn(List.of(given));
        mvc.perform(get("/api/v1/hotels"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(given.getName())));
    }

    @Test
    void getHotelById() throws Exception {
        given(hotelService.getHotelById(given.getId()))
                .willReturn(given);
        mvc.perform(get(String.format("/api/v1/hotels/%d", given.getId())))
                .andExpect(status().isOk());
    }

    @Test
    void addNewHotel() throws Exception {
        String jsonBody = JacksonUtil.serialize(given);
        assertThat(jsonBody).isNotNull();
        mvc.perform(post("/api/v1/hotels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andExpect(status().isOk());
    }

    @Test
    void addNewRoomInHotel() throws Exception {
        given(hotelService.getHotelById(given.getId()))
                .willReturn(given);
        RoomDTO newRoom = new RoomDTO(100, RoomType.STANDART, false, 1500);
        String jsonBody = JacksonUtil.serialize(newRoom);
        assertThat(jsonBody).isNotNull();
        mvc.perform(post(String.format("/api/v1/hotels/%d", given.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andExpect(status().isOk());
    }

    @Test
    void updateHotelInfo() throws Exception {
        given(hotelService.getHotelById(given.getId()))
                .willReturn(given);
        given.setName("NewTestName");
        String jsonBody = JacksonUtil.serialize(given);
        assertThat(jsonBody).isNotNull();
        mvc.perform(put("/api/v1/hotels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andExpect(status().isOk());
    }

    @Test
    void deleteHotelById() throws Exception {
        given(hotelService.getHotelById(given.getId()))
                .willReturn(given);
        mvc.perform(delete(String.format("/api/v1/hotels/%d", given.getId())))
                .andExpect(status().isOk());
    }

    @Test
    void deleteRoomInHotelById() throws Exception {
        given(hotelService.getHotelById(given.getId()))
                .willReturn(given);
        RoomDTO newRoom = new RoomDTO(100, RoomType.STANDART, false, 1500);
        given.getRooms().add(newRoom);
        given(hotelService.addNewRoomByHotelId(given.getId(), newRoom))
                .willReturn(given);
        String jsonBody = JacksonUtil.serialize(given);
        assertThat(jsonBody).isNotNull();
        mvc.perform(delete(String.format("/api/v1/hotels/%d", given.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk());
    }
}