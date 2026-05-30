package com.solvd.smarthome.persistence;

import com.solvd.smarthome.domain.Device;
import java.util.List;

public interface DeviceRepository {
    void create(Device device);
    void update(Device device);
    void delete(Long id);
    Device findById(Long id);
    List<Device> findAll();
    List<Device> findByRoomId(Long roomId);
}
