package com.solvd.smarthome.persistence.impl;

import com.solvd.smarthome.domain.Owner;
import com.solvd.smarthome.persistence.MyBatisSessionHolder;
import com.solvd.smarthome.persistence.OwnerRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class OwnerMapperImpl implements OwnerRepository {

    @Override
    public void create(Owner owner) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            session.getMapper(OwnerRepository.class).create(owner);
        }
    }

    @Override
    public void update(Owner owner) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            session.getMapper(OwnerRepository.class).update(owner);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            session.getMapper(OwnerRepository.class).delete(id);
        }
    }

    @Override
    public Owner findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            return session.getMapper(OwnerRepository.class).findById(id);
        }
    }

    @Override
    public List<Owner> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSession()) {
            return session.getMapper(OwnerRepository.class).findAll();
        }
    }
}
