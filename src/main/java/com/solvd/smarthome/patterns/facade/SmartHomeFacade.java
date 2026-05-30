package com.solvd.smarthome.patterns.facade;

import com.solvd.smarthome.domain.Device;
import com.solvd.smarthome.domain.Owner;
import com.solvd.smarthome.domain.Room;
import com.solvd.smarthome.domain.SmartHome;
import com.solvd.smarthome.service.DeviceService;
import com.solvd.smarthome.service.OwnerService;
import com.solvd.smarthome.service.RoomService;
import com.solvd.smarthome.service.SmartHomeService;
import com.solvd.smarthome.service.impl.DeviceServiceImpl;
import com.solvd.smarthome.service.impl.OwnerServiceImpl;
import com.solvd.smarthome.service.impl.RoomServiceImpl;
import com.solvd.smarthome.service.impl.SmartHomeServiceImpl;

import java.util.List;

public class SmartHomeFacade {

    private final OwnerService ownerService = new OwnerServiceImpl();
    private final SmartHomeService smartHomeService = new SmartHomeServiceImpl();
    private final RoomService roomService = new RoomServiceImpl();
    private final DeviceService deviceService = new DeviceServiceImpl();

    public Owner createOwner(Owner owner) {
        return ownerService.create(owner);
    }

    public SmartHome createSmartHome(SmartHome smartHome) {
        return smartHomeService.create(smartHome);
    }

    public Room createRoom(Room room) {
        return roomService.create(room);
    }

    public Device createDevice(Device device) {
        return deviceService.create(device);
    }

    public List<SmartHome> getAllHomesWithDetails() {
        return smartHomeService.findAllWithDetails();
    }

    public List<Device> getDevicesByRoom(Long roomId) {
        return deviceService.findByRoomId(roomId);
    }
}
