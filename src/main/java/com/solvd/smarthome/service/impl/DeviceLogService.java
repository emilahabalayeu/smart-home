package com.solvd.smarthome.service;

import com.solvd.smarthome.domain.DeviceLog;
import java.util.List;

public interface DeviceLogService {
    DeviceLog create(DeviceLog deviceLog);
    DeviceLog update(DeviceLog deviceLog);
    void delete(Long id);
    DeviceLog findById(Long id);
    List<DeviceLog> findAll();
    List<DeviceLog> findByDeviceId(Long deviceId);
}
