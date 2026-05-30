package com.solvd.smarthome.service.impl;

import com.solvd.smarthome.domain.DeviceLog;
import com.solvd.smarthome.persistence.DeviceLogRepository;
import com.solvd.smarthome.persistence.impl.DeviceLogMapperImpl;
import com.solvd.smarthome.service.DeviceLogService;

import java.util.List;

public class DeviceLogServiceImpl implements DeviceLogService {

    private final DeviceLogRepository deviceLogRepository = new DeviceLogMapperImpl();

    @Override
    public DeviceLog create(DeviceLog deviceLog) {
        deviceLogRepository.create(deviceLog);
        return deviceLog;
    }

    @Override
    public DeviceLog update(DeviceLog deviceLog) {
        deviceLogRepository.update(deviceLog);
        return deviceLog;
    }

    @Override
    public void delete(Long id) {
        deviceLogRepository.delete(id);
    }

    @Override
    public DeviceLog findById(Long id) {
        return deviceLogRepository.findById(id);
    }

    @Override
    public List<DeviceLog> findAll() {
        return deviceLogRepository.findAll();
    }

    @Override
    public List<DeviceLog> findByDeviceId(Long deviceId) {
        return deviceLogRepository.findByDeviceId(deviceId);
    }
}
