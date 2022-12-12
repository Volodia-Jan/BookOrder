package com.application.bookorder.mapper;

import com.application.bookorder.dto.HotelDTO;
import com.application.bookorder.dto.RoomDTO;
import com.application.bookorder.entity.Hotel;
import com.application.bookorder.entity.Room;

import java.util.ArrayList;
import java.util.List;

public class HotelMapper {

    public static Hotel toEntity(HotelDTO dto){
        Hotel entity = new Hotel();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        List<Room> rooms = new ArrayList<>();
        if (dto.getRooms() != null)
            rooms = dto.getRooms().stream().map(RoomMapper::toEntity).toList();
        entity.setRooms(new ArrayList<>(rooms));
        return entity;
    }

    public static HotelDTO toDTO(Hotel entity){
        HotelDTO dto = new HotelDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        List<RoomDTO> rooms = new ArrayList<>();
        if (entity.getRooms() != null)
            rooms = entity.getRooms().stream().map(RoomMapper::toDTO).toList();
        dto.setRooms(new ArrayList<>(rooms));
        return dto;
    }
}
