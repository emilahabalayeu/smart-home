package com.solvd.smarthome.patterns;

import com.solvd.smarthome.domain.Device;

public class DeviceFactory {

    public static Device createDevice(String type, String name, String model) {
        Device device = new Device();
        device.setName(name);
        device.setModel(model);
        device.setOnline(false);
        return device;
    }
}
