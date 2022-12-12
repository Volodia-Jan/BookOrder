package com.application.bookorder.repository;

import com.application.bookorder.entity.Hotel;
import org.springframework.stereotype.Repository;

@Repository
public class HotelRepository extends BaseRepository<Hotel>{

    @Override
    public Hotel save(Hotel entity) {
        Hotel newHotel = new Hotel();
        newHotel.setName(entity.getName());
        newHotel.setRooms(entity.getRooms());
        return super.save(newHotel);
    }

    @Override
    public Hotel update(Hotel entity) {
        Hotel updated = findById(entity.getId());
        updated.setName(entity.getName());
        updated.setRooms(entity.getRooms());
        return updated;
    }
}
