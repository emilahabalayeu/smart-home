package com.solvd.smarthome.service;

import com.solvd.smarthome.domain.Device;
import com.solvd.smarthome.domain.Owner;
import com.solvd.smarthome.domain.Room;
import com.solvd.smarthome.domain.SmartHome;
import com.solvd.smarthome.service.impl.DeviceServiceImpl;
import com.solvd.smarthome.service.impl.OwnerServiceImpl;
import com.solvd.smarthome.service.impl.RoomServiceImpl;
import com.solvd.smarthome.service.impl.SmartHomeServiceImpl;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

public class DeviceServiceTest {

    private com.solvd.smarthome.service.DeviceService deviceService;
    private Device testDevice;
    private Long roomId;

    @BeforeClass
    public void beforeClass() {
        deviceService = new DeviceServiceImpl();

        Owner owner = new Owner();
        owner.setFirstName("Device");
        owner.setLastName("Tester");
        owner.setEmail("devicetest" + System.currentTimeMillis() + "@example.com");
        new OwnerServiceImpl().create(owner);

        SmartHome smartHome = new SmartHome();
        smartHome.setAddress("Device Test Address");
        smartHome.setOwnerId(owner.getId());
        new SmartHomeServiceImpl().create(smartHome);

        Room room = new Room();
        room.setName("Device Test Room");
        room.setAreaSqMeters(15.0);
        room.setSmartHomeId(smartHome.getId());
        new RoomServiceImpl().create(room);
        roomId = room.getId();
    }

    @BeforeMethod
    public void beforeMethod() {
        testDevice = new Device();
        testDevice.setName("Test Device");
        testDevice.setModel("Test Model");
        testDevice.setOnline(false);
        testDevice.setInstalledAt(LocalDateTime.now());
        testDevice.setRoomId(roomId);
    }

    @Test
    public void testCreateDevice() {
        deviceService.create(testDevice);
        Assert.assertNotNull(testDevice.getId(), "Device ID should not be null after creation");
    }

    @Test
    public void testFindDeviceById() {
        deviceService.create(testDevice);
        Device found = deviceService.findById(testDevice.getId());
        Assert.assertEquals(found.getName(), testDevice.getName(), "Device name should match");
    }

    @Test
    public void testFindAllDevices() {
        deviceService.create(testDevice);
        List<Device> devices = deviceService.findAll();
        Assert.assertFalse(devices.isEmpty(), "Device list should not be empty");
    }

    @Test
    public void testUpdateDevice() {
        deviceService.create(testDevice);
        testDevice.setName("Updated Device");
        deviceService.update(testDevice);
        Device updated = deviceService.findById(testDevice.getId());
        Assert.assertEquals(updated.getName(), "Updated Device", "Device name should be updated");
    }

    @Test
    public void testFindDevicesByRoomId() {
        deviceService.create(testDevice);
        List<Device> devices = deviceService.findByRoomId(roomId);
        Assert.assertFalse(devices.isEmpty(), "Devices list by room should not be empty");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("DeviceServiceTest finished");
    }
}
