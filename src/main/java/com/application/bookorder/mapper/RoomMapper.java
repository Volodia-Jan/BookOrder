package com.application.bookorder.mapper;

import com.application.bookorder.dto.RoomDTO;
import com.application.bookorder.entity.Room;

public class RoomMapper {

    public static Room toEntity(RoomDTO dto) {
        Room entity = new Room();
        entity.setNumber(dto.getNumber());
        entity.setType(dto.getType());
        entity.setBooked(dto.isBooked());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    public static RoomDTO toDTO(Room entity) {
        RoomDTO dto = new RoomDTO();
        dto.setType(entity.getType());
        dto.setNumber(entity.getNumber());
        dto.setBooked(entity.isBooked());
        dto.setPrice(entity.getPrice());
        return dto;
    }
}
