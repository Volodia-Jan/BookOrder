package com.application.bookorder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    private Hotel hotel;
    private Room room;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetail other = (OrderDetail) o;
        return hotel.equals(other.hotel) &&
                room.equals(other.room);
    }

    @Override
    public int hashCode() {
        int result = hotel != null ? hotel.hashCode() : 0;
        result = 31 * result + (room != null ? room.hashCode() : 0);
        return result;
    }
}
