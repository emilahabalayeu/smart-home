package com.solvd.smarthome.persistence.impl;

import com.solvd.smarthome.domain.DeviceLog;
import com.solvd.smarthome.persistence.DeviceLogRepository;
import com.solvd.smarthome.persistence.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class DeviceLogMapperImpl implements DeviceLogRepository {

    @Override
    public void create(DeviceLog deviceLog) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            session.getMapper(DeviceLogRepository.class).create(deviceLog);
        }
    }

    @Override
    public void update(DeviceLog deviceLog) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            session.getMapper(DeviceLogRepository.class).update(deviceLog);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            session.getMapper(DeviceLogRepository.class).delete(id);
        }
    }

    @Override
    public DeviceLog findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            return session.getMapper(DeviceLogRepository.class).findById(id);
        }
    }

    @Override
    public List<DeviceLog> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            return session.getMapper(DeviceLogRepository.class).findAll();
        }
    }

    @Override
    public List<DeviceLog> findByDeviceId(Long deviceId) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            return session.getMapper(DeviceLogRepository.class).findByDeviceId(deviceId);
        }
    }
}
