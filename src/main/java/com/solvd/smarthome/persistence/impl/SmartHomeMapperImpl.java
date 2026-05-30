package com.solvd.smarthome.persistence.impl;

import com.solvd.smarthome.domain.SmartHome;
import com.solvd.smarthome.persistence.MyBatisSessionHolder;
import com.solvd.smarthome.persistence.SmartHomeRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class SmartHomeMapperImpl implements SmartHomeRepository {

    @Override
    public void create(SmartHome smartHome) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            session.getMapper(SmartHomeRepository.class).create(smartHome);
        }
    }

    @Override
    public void update(SmartHome smartHome) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            session.getMapper(SmartHomeRepository.class).update(smartHome);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            session.getMapper(SmartHomeRepository.class).delete(id);
        }
    }

    @Override
    public SmartHome findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            return session.getMapper(SmartHomeRepository.class).findById(id);
        }
    }

    @Override
    public List<SmartHome> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            return session.getMapper(SmartHomeRepository.class).findAll();
        }
    }

    @Override
    public List<SmartHome> findByOwnerId(Long ownerId) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            return session.getMapper(SmartHomeRepository.class).findByOwnerId(ownerId);
        }
    }

    @Override
    public List<SmartHome> findAllWithDetails() {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            return session.getMapper(SmartHomeRepository.class).findAllWithDetails();
        }
    }
}
