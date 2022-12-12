package com.application.bookorder.service.impl;

import com.application.bookorder.dto.HotelDTO;
import com.application.bookorder.dto.RoomDTO;
import com.application.bookorder.entity.Hotel;
import com.application.bookorder.entity.Room;
import com.application.bookorder.mapper.HotelMapper;
import com.application.bookorder.mapper.RoomMapper;
import com.application.bookorder.repository.HotelRepository;
import com.application.bookorder.service.HotelService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<HotelDTO> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(HotelMapper::toDTO)
                .toList();
    }

    @Override
    public HotelDTO addNewHotel(HotelDTO hotelDTO) {
        Hotel entity = HotelMapper.toEntity(hotelDTO);
        return HotelMapper.toDTO(hotelRepository.save(entity));
    }

    @Override
    public HotelDTO getHotelById(Long hotelId) {
        return HotelMapper.toDTO(hotelRepository.findById(hotelId));
    }

    @Override
    public HotelDTO addNewRoomByHotelId(Long hotelId, RoomDTO roomDTO) {
        Hotel hotel = hotelRepository.findById(hotelId);
        hotel.getRooms().add(RoomMapper.toEntity(roomDTO));
        return HotelMapper.toDTO(hotelRepository.update(hotel));
    }

    @Override
    public HotelDTO deleteRoomInHotel(Long hotelId, Integer roomNumber) {
        Hotel hotel = hotelRepository.findById(hotelId);
        List<Room> updatedRooms = hotel.getRooms().stream()
                .filter(room -> !room.getNumber().equals(roomNumber))
                .toList();
        hotel.setRooms(new ArrayList<>(updatedRooms));
        return HotelMapper.toDTO(hotelRepository.update(hotel));
    }

    @Override
    public HotelDTO updateHotelInfo(HotelDTO hotelDTO) {
        Hotel entity = HotelMapper.toEntity(hotelDTO);
        return HotelMapper.toDTO(hotelRepository.update(entity));
    }

    @Override
    public void deleteHotelById(Long hotelId) {
        hotelRepository.deleteById(hotelId);
    }
}
