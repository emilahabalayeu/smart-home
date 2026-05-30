package com.solvd.smarthome.patterns.factory;

import com.solvd.smarthome.domain.Device;
import com.solvd.smarthome.domain.Room;
import com.solvd.smarthome.domain.SmartHome;

public interface AbstractSmartHomeFactory {
    SmartHome createSmartHome(String address);
    Room createRoom(String name, double area);
    Device createDevice(String name, String model);
}
