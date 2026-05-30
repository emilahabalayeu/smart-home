package com.solvd.smarthome.persistence;

import com.solvd.smarthome.domain.SmartHome;
import java.util.List;

public interface SmartHomeRepository {
    void create(SmartHome smartHome);
    void update(SmartHome smartHome);
    void delete(Long id);
    SmartHome findById(Long id);
    List<SmartHome> findAll();
    List<SmartHome> findByOwnerId(Long ownerId);
    List<SmartHome> findAllWithDetails();
}