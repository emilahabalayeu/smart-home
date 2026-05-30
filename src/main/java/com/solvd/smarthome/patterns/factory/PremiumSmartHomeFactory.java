package com.solvd.smarthome.patterns.factory;

import com.solvd.smarthome.domain.Device;
import com.solvd.smarthome.domain.Room;
import com.solvd.smarthome.domain.SmartHome;

public class PremiumSmartHomeFactory implements AbstractSmartHomeFactory {

    @Override
    public SmartHome createSmartHome(String address) {
        SmartHome smartHome = new SmartHome();
        smartHome.setAddress("PREMIUM: " + address);
        return smartHome;
    }

    @Override
    public Room createRoom(String name, double area) {
        Room room = new Room();
        room.setName("Premium " + name);
        room.setAreaSqMeters(area * 1.5);
        return room;
    }

    @Override
    public Device createDevice(String name, String model) {
        Device device = new Device();
        device.setName("Premium " + name);
        device.setModel("Pro " + model);
        device.setOnline(true);
        return device;
    }
}
