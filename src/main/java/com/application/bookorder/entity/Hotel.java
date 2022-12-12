package com.application.bookorder.entity;

import lombok.Data;

import java.util.List;

@Data
public class Hotel extends BaseEntity{

    private String name;
    private List<Room> rooms;

    public Hotel() {
    }

    public Hotel(Long id, String name, List<Room> rooms) {
        super(id);
        this.name = name;
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Hotel other = (Hotel) o;
        return id.equals(other.id) &&
                name .equals(other.name) &&
                rooms.equals(other.rooms);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (rooms != null ? rooms.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}
