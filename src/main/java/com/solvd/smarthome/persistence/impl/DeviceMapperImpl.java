package com.solvd.smarthome.persistence.impl;

import com.solvd.smarthome.domain.Device;
import com.solvd.smarthome.persistence.DeviceRepository;
import com.solvd.smarthome.persistence.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class DeviceMapperImpl implements DeviceRepository {

    @Override
    public void create(Device device) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            session.getMapper(DeviceRepository.class).create(device);
        }
    }

    @Override
    public void update(Device device) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            session.getMapper(DeviceRepository.class).update(device);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            session.getMapper(DeviceRepository.class).delete(id);
        }
    }

    @Override
    public Device findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            return session.getMapper(DeviceRepository.class).findById(id);
        }
    }

    @Override
    public List<Device> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            return session.getMapper(DeviceRepository.class).findAll();
        }
    }

    @Override
    public List<Device> findByRoomId(Long roomId) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            return session.getMapper(DeviceRepository.class).findByRoomId(roomId);
        }
    }
}
