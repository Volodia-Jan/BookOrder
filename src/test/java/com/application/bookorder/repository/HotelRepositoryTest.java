package com.application.bookorder.repository;

import com.application.bookorder.entity.Hotel;
import com.application.bookorder.entity.Room;
import com.application.bookorder.entity.enumeration.RoomType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HotelRepositoryTest {

    private HotelRepository underTest;
    private Hotel given;

    @BeforeEach
    void setUp() {
        underTest = new HotelRepository();
        given = new Hotel();
        given.setId(0L);
        given.setName("Sultan");
        given.setRooms(List.of(
                new Room(205, RoomType.STANDART, false, 2000),
                new Room(205, RoomType.STUDIO, true, 4000),
                new Room(205, RoomType.SUPERIOR, false, 3050),
                new Room(205, RoomType.CORNER_ROOM, true, 5000)
        ));
    }

    @AfterEach
    void tearDown() {
        given = null;
    }

    @Test
    void itShouldSaveNewHotel() {
        Hotel saved = underTest.save(given);
        assertThat(saved.getId()).isNotZero();
        assertThat(saved.getName()).isEqualTo(given.getName());
        assertThat(saved.getRooms()).isEqualTo(given.getRooms());
    }

    @Test
    void itShouldUpdateExistedHotel() {
        Hotel saved = underTest.save(given);
        saved.setRooms(null);
        saved.setName("NewTestName");
        Hotel updated = underTest.update(saved);
        assertThat(updated).isEqualTo(saved);
    }
}