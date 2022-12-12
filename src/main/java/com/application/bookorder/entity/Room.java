package com.application.bookorder.entity;

import com.application.bookorder.entity.enumeration.RoomType;
import lombok.Data;

@Data
public class Room {
    private Integer number;
    private RoomType type;
    private boolean isBooked;
    private Integer price;

    public Room() {
    }

    public Room(Integer number, RoomType type, boolean isBooked, Integer price) {
        this.number = number;
        this.type = type;
        this.isBooked = isBooked;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Room other = (Room) o;

        return number.equals(other.number) &&
                type.equals(other.type) &&
                isBooked == other.isBooked &&
                price.equals(other.price);
    }

    @Override
    public int hashCode() {
        int result = (number != null ? number.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (isBooked ? 1 : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Room{" +
                "number=" + number +
                ", type=" + type +
                ", isBooked=" + isBooked +
                ", price=" + price + '}';
    }
}
