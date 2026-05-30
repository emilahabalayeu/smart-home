package com.solvd.smarthome.service;

import com.solvd.smarthome.domain.Device;
import java.util.List;

public interface DeviceService {
    Device create(Device device);
    Device update(Device device);
    void delete(Long id);
    Device findById(Long id);
    List<Device> findAll();
    List<Device> findByRoomId(Long roomId);
}
