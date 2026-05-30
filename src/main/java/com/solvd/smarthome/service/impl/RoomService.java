package com.solvd.smarthome.service;

import com.solvd.smarthome.domain.Room;
import java.util.List;

public interface RoomService {
    Room create(Room room);
    Room update(Room room);
    void delete(Long id);
    Room findById(Long id);
    List<Room> findAll();
}
