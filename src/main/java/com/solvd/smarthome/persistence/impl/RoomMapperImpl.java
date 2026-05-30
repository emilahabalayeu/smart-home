package com.solvd.smarthome.persistence.impl;

import com.solvd.smarthome.domain.Room;
import com.solvd.smarthome.persistence.MyBatisSessionHolder;
import com.solvd.smarthome.persistence.RoomRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class RoomMapperImpl implements RoomRepository {

    @Override
    public void create(Room room) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            session.getMapper(RoomRepository.class).create(room);
        }
    }

    @Override
    public void update(Room room) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            session.getMapper(RoomRepository.class).update(room);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            session.getMapper(RoomRepository.class).delete(id);
        }
    }

    @Override
    public Room findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            return session.getMapper(RoomRepository.class).findById(id);
        }
    }

    @Override
    public List<Room> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            return session.getMapper(RoomRepository.class).findAll();
        }
    }
}
