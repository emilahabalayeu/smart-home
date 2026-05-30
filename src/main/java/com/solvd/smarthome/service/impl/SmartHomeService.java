package com.solvd.smarthome.service;

import com.solvd.smarthome.domain.SmartHome;
import java.util.List;

public interface SmartHomeService {
    SmartHome create(SmartHome smartHome);
    SmartHome update(SmartHome smartHome);
    void delete(Long id);
    SmartHome findById(Long id);
    List<SmartHome> findAll();
    List<SmartHome> findByOwnerId(Long ownerId);
    List<SmartHome> findAllWithDetails();
}
