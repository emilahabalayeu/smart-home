package com.solvd.smarthome.patterns.decorator;

import com.solvd.smarthome.domain.Device;
import com.solvd.smarthome.service.DeviceService;

import java.util.List;

public abstract class DeviceServiceDecorator implements DeviceService {

    protected final DeviceService deviceService;

    public DeviceServiceDecorator(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @Override
    public Device create(Device device) {
        return deviceService.create(device);
    }

    @Override
    public Device update(Device device) {
        return deviceService.update(device);
    }

    @Override
    public void delete(Long id) {
        deviceService.delete(id);
    }

    @Override
    public Device findById(Long id) {
        return deviceService.findById(id);
    }

    @Override
    public List<Device> findAll() {
        return deviceService.findAll();
    }

    @Override
    public List<Device> findByRoomId(Long roomId) {
        return deviceService.findByRoomId(roomId);
    }
}
