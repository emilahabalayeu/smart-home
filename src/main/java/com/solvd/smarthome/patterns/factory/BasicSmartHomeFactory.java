package com.solvd.smarthome.patterns.factory;

import com.solvd.smarthome.domain.Device;
import com.solvd.smarthome.domain.Room;
import com.solvd.smarthome.domain.SmartHome;

public class BasicSmartHomeFactory implements AbstractSmartHomeFactory {

    @Override
    public SmartHome createSmartHome(String address) {
        SmartHome smartHome = new SmartHome();
        smartHome.setAddress(address);
        return smartHome;
    }

    @Override
    public Room createRoom(String name, double area) {
        Room room = new Room();
        room.setName(name);
        room.setAreaSqMeters(area);
        return room;
    }

    @Override
    public Device createDevice(String name, String model) {
        Device device = new Device();
        device.setName(name);
        device.setModel(model);
        device.setOnline(false);
        return device;
    }
}
