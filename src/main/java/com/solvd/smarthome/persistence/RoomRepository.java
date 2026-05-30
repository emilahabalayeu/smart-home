package com.solvd.smarthome.persistence;

import com.solvd.smarthome.domain.Room;
import java.util.List;

public interface RoomRepository {
    void create(Room room);
    void update(Room room);
    void delete(Long id);
    Room findById(Long id);
    List<Room> findAll();
}