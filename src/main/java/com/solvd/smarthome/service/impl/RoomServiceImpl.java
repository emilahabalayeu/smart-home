package com.solvd.smarthome.service.impl;

import com.solvd.smarthome.domain.Room;
import com.solvd.smarthome.persistence.RoomRepository;
import com.solvd.smarthome.persistence.impl.RoomRepositoryImpl;
import com.solvd.smarthome.service.RoomService;

import java.util.List;

public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository = new RoomRepositoryImpl();

    @Override
    public Room create(Room room) {
        roomRepository.create(room);
        return room;
    }

    @Override
    public Room update(Room room) {
        roomRepository.update(room);
        return room;
    }

    @Override
    public void delete(Long id) {
        roomRepository.delete(id);
    }

    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }
}
