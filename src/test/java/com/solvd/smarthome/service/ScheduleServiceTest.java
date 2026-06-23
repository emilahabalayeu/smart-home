package com.solvd.smarthome.service;

import com.solvd.smarthome.domain.*;
import com.solvd.smarthome.service.impl.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ScheduleServiceTest {

    private com.solvd.smarthome.service.ScheduleService scheduleService;
    private Schedule testSchedule;
    private Long deviceId;

    @BeforeClass
    public void beforeClass() {
        scheduleService = new ScheduleServiceImpl();

        Owner owner = new Owner();
        owner.setFirstName("Schedule");
        owner.setLastName("Tester");
        owner.setEmail("scheduletest" + System.currentTimeMillis() + "@example.com");
        new OwnerServiceImpl().create(owner);

        SmartHome smartHome = new SmartHome();
        smartHome.setAddress("Schedule Test Address");
        smartHome.setOwnerId(owner.getId());
        new SmartHomeServiceImpl().create(smartHome);

        Room room = new Room();
        room.setName("Schedule Test Room");
        room.setAreaSqMeters(10.0);
        room.setSmartHomeId(smartHome.getId());
        new RoomServiceImpl().create(room);

        Device device = new Device();
        device.setName("Schedule Test Device");
        device.setModel("Test Model");
        device.setOnline(false);
        device.setInstalledAt(LocalDateTime.now());
        device.setRoomId(room.getId());
        new DeviceServiceImpl().create(device);
        deviceId = device.getId();
    }

    @BeforeMethod
    public void beforeMethod() {
        testSchedule = new Schedule();
        testSchedule.setDeviceId(deviceId);
        testSchedule.setTurnOnTime(LocalTime.of(7, 0));
        testSchedule.setTurnOffTime(LocalTime.of(23, 0));
        testSchedule.setStartDate(LocalDate.now());
        testSchedule.setActive(true);
    }

    @Test
    public void testCreateSchedule() {
        scheduleService.create(testSchedule);
        Assert.assertNotNull(testSchedule.getId(), "Schedule ID should not be null after creation");
    }

    @Test
    public void testFindScheduleById() {
        scheduleService.create(testSchedule);
        Schedule found = scheduleService.findById(testSchedule.getId());
        Assert.assertEquals(found.getDeviceId(), testSchedule.getDeviceId(), "Device ID should match");
    }

    @Test
    public void testFindAllSchedules() {
        scheduleService.create(testSchedule);
        List<Schedule> schedules = scheduleService.findAll();
        Assert.assertFalse(schedules.isEmpty(), "Schedule list should not be empty");
    }

    @Test
    public void testUpdateSchedule() {
        scheduleService.create(testSchedule);
        testSchedule.setTurnOnTime(LocalTime.of(8, 0));
        scheduleService.update(testSchedule);
        Schedule updated = scheduleService.findById(testSchedule.getId());
        Assert.assertEquals(updated.getTurnOnTime(), LocalTime.of(8, 0), "Turn on time should be updated");
    }

    @Test
    public void testFindSchedulesByDeviceId() {
        scheduleService.create(testSchedule);
        List<Schedule> schedules = scheduleService.findByDeviceId(deviceId);
        Assert.assertFalse(schedules.isEmpty(), "Schedule list by device should not be empty");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("ScheduleServiceTest finished");
    }
}
