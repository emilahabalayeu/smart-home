package com.solvd.smarthome.service;

import com.solvd.smarthome.domain.*;
import com.solvd.smarthome.service.impl.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

public class DeviceLogServiceTest {

    private com.solvd.smarthome.service.DeviceLogService deviceLogService;
    private DeviceLog testLog;
    private Long deviceId;

    @BeforeClass
    public void beforeClass() {
        deviceLogService = new DeviceLogServiceImpl();

        Owner owner = new Owner();
        owner.setFirstName("Log");
        owner.setLastName("Tester");
        owner.setEmail("logtest" + System.currentTimeMillis() + "@example.com");
        new OwnerServiceImpl().create(owner);

        SmartHome smartHome = new SmartHome();
        smartHome.setAddress("Log Test Address");
        smartHome.setOwnerId(owner.getId());
        new SmartHomeServiceImpl().create(smartHome);

        Room room = new Room();
        room.setName("Log Test Room");
        room.setAreaSqMeters(10.0);
        room.setSmartHomeId(smartHome.getId());
        new RoomServiceImpl().create(room);

        Device device = new Device();
        device.setName("Log Test Device");
        device.setModel("Test Model");
        device.setOnline(false);
        device.setInstalledAt(LocalDateTime.now());
        device.setRoomId(room.getId());
        new DeviceServiceImpl().create(device);
        deviceId = device.getId();
    }

    @BeforeMethod
    public void beforeMethod() {
        testLog = new DeviceLog();
        testLog.setDeviceId(deviceId);
        testLog.setMessage("Test log");
        testLog.setTimestamp(LocalDateTime.now());
    }

    @Test
    public void testCreateDeviceLog() {
        deviceLogService.create(testLog);
        Assert.assertNotNull(testLog.getId(), "DeviceLog ID should not be null after creation");
    }

    @Test
    public void testFindDeviceLogById() {
        deviceLogService.create(testLog);
        DeviceLog found = deviceLogService.findById(testLog.getId());
        Assert.assertEquals(found.getMessage(), testLog.getMessage(), "Message should match");
    }

    @Test
    public void testFindAllDeviceLogs() {
        deviceLogService.create(testLog);
        List<DeviceLog> logs = deviceLogService.findAll();
        Assert.assertFalse(logs.isEmpty(), "DeviceLog list should not be empty");
    }

    @Test
    public void testUpdateDeviceLog() {
        deviceLogService.create(testLog);
        testLog.setMessage("Updated log");
        deviceLogService.update(testLog);
        DeviceLog updated = deviceLogService.findById(testLog.getId());
        Assert.assertEquals(updated.getMessage(), "Updated log", "Message should be updated");
    }

    @Test
    public void testFindDeviceLogsByDeviceId() {
        deviceLogService.create(testLog);
        List<DeviceLog> logs = deviceLogService.findByDeviceId(deviceId);
        Assert.assertFalse(logs.isEmpty(), "DeviceLog list by device should not be empty");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("DeviceLogServiceTest finished");
    }
}
