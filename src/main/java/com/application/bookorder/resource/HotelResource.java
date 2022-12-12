package com.application.bookorder.resource;

import com.application.bookorder.dto.HotelDTO;
import com.application.bookorder.dto.RoomDTO;
import com.application.bookorder.service.HotelService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/hotels")
public class HotelResource {

    private final HotelService hotelService;

    public HotelResource(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<HotelDTO> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping(path = "/{id}")
    public HotelDTO getHotelById(@PathVariable("id") Long hotelId) {
        return hotelService.getHotelById(hotelId);
    }

    @PostMapping
    public HotelDTO addNewHotel(@RequestBody @Validated HotelDTO hotelDTO) {
        return hotelService.addNewHotel(hotelDTO);
    }

    @PostMapping(path = "/{id}")
    public HotelDTO addNewRoomInHotel(@PathVariable("id") Long hotelId,
                                      @RequestBody @Validated RoomDTO roomDTO) {
        return hotelService.addNewRoomByHotelId(hotelId, roomDTO);
    }

    @PutMapping
    public HotelDTO updateHotelInfo(@RequestBody HotelDTO hotelDTO) {
        return hotelService.updateHotelInfo(hotelDTO);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteHotelById(@PathVariable("id") Long hotelId) {
        hotelService.deleteHotelById(hotelId);
    }

    @DeleteMapping(
            path = "/{id}",
            params = {"roomNumber"}
    )
    public HotelDTO deleteRoomInHotelById(@PathVariable("id") Long hotelId,
                                    @RequestParam("roomNumber") Integer roomNumber) {
        return hotelService.deleteRoomInHotel(hotelId, roomNumber);
    }
}
