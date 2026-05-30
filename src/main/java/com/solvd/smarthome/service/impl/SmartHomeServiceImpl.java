package com.solvd.smarthome.service.impl;

import com.solvd.smarthome.domain.SmartHome;
import com.solvd.smarthome.persistence.SmartHomeRepository;
import com.solvd.smarthome.persistence.impl.SmartHomeMapperImpl;
import com.solvd.smarthome.service.SmartHomeService;

import java.util.List;

public class SmartHomeServiceImpl implements SmartHomeService {

    private final SmartHomeRepository smartHomeRepository = new SmartHomeMapperImpl();

    @Override
    public SmartHome create(SmartHome smartHome) {
        smartHomeRepository.create(smartHome);
        return smartHome;
    }

    @Override
    public SmartHome update(SmartHome smartHome) {
        smartHomeRepository.update(smartHome);
        return smartHome;
    }

    @Override
    public void delete(Long id) {
        smartHomeRepository.delete(id);
    }

    @Override
    public SmartHome findById(Long id) {
        return smartHomeRepository.findById(id);
    }

    @Override
    public List<SmartHome> findAll() {
        return smartHomeRepository.findAll();
    }

    @Override
    public List<SmartHome> findByOwnerId(Long ownerId) {
        return smartHomeRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<SmartHome> findAllWithDetails() {
        return smartHomeRepository.findAllWithDetails();
    }
}
