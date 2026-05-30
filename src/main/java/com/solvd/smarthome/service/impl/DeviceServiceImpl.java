package com.solvd.smarthome.service.impl;

import com.solvd.smarthome.domain.Device;
import com.solvd.smarthome.persistence.DeviceRepository;
import com.solvd.smarthome.persistence.impl.DeviceRepositoryImpl;
import com.solvd.smarthome.service.DeviceService;

import java.util.List;

public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository = new DeviceRepositoryImpl();

    @Override
    public Device create(Device device) {
        deviceRepository.create(device);
        return device;
    }

    @Override
    public Device update(Device device) {
        deviceRepository.update(device);
        return device;
    }

    @Override
    public void delete(Long id) {
        deviceRepository.delete(id);
    }

    @Override
    public Device findById(Long id) {
        return deviceRepository.findById(id);
    }

    @Override
    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    @Override
    public List<Device> findByRoomId(Long roomId) {
        return deviceRepository.findByRoomId(roomId);
    }
}
