package com.solvd.smarthome.persistence;

import com.solvd.smarthome.domain.DeviceLog;
import java.util.List;

public interface DeviceLogRepository {
    void create(DeviceLog deviceLog);
    void update(DeviceLog deviceLog);
    void delete(Long id);
    DeviceLog findById(Long id);
    List<DeviceLog> findAll();
    List<DeviceLog> findByDeviceId(Long deviceId);
}
