package com.solvd.smarthome.persistence.impl;

import com.solvd.smarthome.domain.Schedule;
import com.solvd.smarthome.persistence.MyBatisSessionHolder;
import com.solvd.smarthome.persistence.ScheduleRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ScheduleMapperImpl implements ScheduleRepository {

    @Override
    public void create(Schedule schedule) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            session.getMapper(ScheduleRepository.class).create(schedule);
        }
    }

    @Override
    public void update(Schedule schedule) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            session.getMapper(ScheduleRepository.class).update(schedule);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            session.getMapper(ScheduleRepository.class).delete(id);
        }
    }

    @Override
    public Schedule findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            return session.getMapper(ScheduleRepository.class).findById(id);
        }
    }

    @Override
    public List<Schedule> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            return session.getMapper(ScheduleRepository.class).findAll();
        }
    }

    @Override
    public List<Schedule> findByDeviceId(Long deviceId) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            return session.getMapper(ScheduleRepository.class).findByDeviceId(deviceId);
        }
    }
}
