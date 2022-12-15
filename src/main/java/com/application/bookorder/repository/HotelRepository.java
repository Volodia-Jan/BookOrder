package com.application.bookorder.repository;

import com.application.bookorder.entity.Hotel;
import com.application.bookorder.entity.Room;
import com.application.bookorder.entity.enumeration.RoomType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HotelRepository extends BaseRepository<Hotel>{

    public HotelRepository() {
        super();
        save(new Hotel(0L, "Aqua", new ArrayList<>(
                List.of(
                        new Room(203, RoomType.BEDROOM, false, 2500),
                        new Room(204, RoomType.STANDART, false, 1700),
                        new Room(205, RoomType.STUDIO, true, 3000),
                        new Room(206, RoomType.SUPERIOR, false, 2200)
                )
        )));
        save(new Hotel(0L, "Sultan", new ArrayList<>(
                List.of(
                        new Room(303, RoomType.CORNER_ROOM, true, 4100),
                        new Room(304, RoomType.SUPERIOR, false, 2700),
                        new Room(305, RoomType.STANDART, false, 2100),
                        new Room(306, RoomType.BEDROOM, true, 2300)
                )
        )));
        save(new Hotel(0L, "Palmira", new ArrayList<>(
                List.of(
                        new Room(123, RoomType.CORNER_ROOM, false, 5000),
                        new Room(124, RoomType.STANDART, false, 2500),
                        new Room(125, RoomType.STUDIO, false, 3500),
                        new Room(126, RoomType.SUPERIOR, false, 3000)
                )
        )));
    }

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
