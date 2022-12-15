package com.application.bookorder.service;

import com.application.bookorder.dto.HotelDTO;
import com.application.bookorder.dto.RoomDTO;

import java.util.List;

public interface HotelService {

    List<HotelDTO> getAllHotels();

    HotelDTO addNewHotel(HotelDTO hotelDTO);

    HotelDTO updateHotelInfo(HotelDTO hotelDTO);

    void deleteHotelById(Long hotelId);

    HotelDTO getHotelById(Long hotelId);

    HotelDTO addNewRoomByHotelId(Long hotelId, RoomDTO roomDTO);

    HotelDTO deleteRoomInHotel(Long hotelId, Integer roomNumber);
}
