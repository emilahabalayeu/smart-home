package com.solvd.smarthome.patterns.decorator;

import com.solvd.smarthome.domain.Device;
import com.solvd.smarthome.service.DeviceService;

import java.util.List;

public class LoggingDeviceService extends DeviceServiceDecorator {

    public LoggingDeviceService(DeviceService deviceService) {
        super(deviceService);
    }

    @Override
    public Device create(Device device) {
        System.out.println("Creating device: " + device.getName());
        Device result = super.create(device);
        System.out.println("Device created with id: " + result.getId());
        return result;
    }

    @Override
    public void delete(Long id) {
        System.out.println("Deleting device with id: " + id);
        super.delete(id);
        System.out.println("Device deleted");
    }

    @Override
    public List<Device> findAll() {
        System.out.println("Fetching all devices...");
        List<Device> devices = super.findAll();
        System.out.println("Found " + devices.size() + " devices");
        return devices;
    }
}
