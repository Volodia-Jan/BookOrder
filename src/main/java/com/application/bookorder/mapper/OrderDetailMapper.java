package com.application.bookorder.mapper;

import com.application.bookorder.dto.OrderDetailDTO;
import com.application.bookorder.entity.Hotel;
import com.application.bookorder.entity.OrderDetail;
import com.application.bookorder.repository.HotelRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailMapper {

    private final HotelRepository hotelRepository;

    public OrderDetailMapper(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public OrderDetail toEntity(OrderDetailDTO dto){
        OrderDetail entity = new OrderDetail();
        Hotel hotel = hotelRepository.findById(dto.getHotelId());
        entity.setHotel(hotel);
        entity.setRoom(hotel.getRooms().stream()
                .filter(r -> r.getNumber().equals(dto.getRoomNumber()))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(String.format("Room not found by number:%d", dto.getRoomNumber()))));
        return entity;
    }

    public OrderDetailDTO toDTO(OrderDetail entity){
        OrderDetailDTO dto = new OrderDetailDTO();
        dto.setHotelId(entity.getHotel().getId());
        dto.setRoomNumber(entity.getRoom().getNumber());
        return dto;
    }
}
