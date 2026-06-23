package com.solvd.smarthome.service;

import com.solvd.smarthome.service.RoomService;
import com.solvd.smarthome.service.SmartHomeService;
import com.solvd.smarthome.service.OwnerService;
import com.solvd.smarthome.domain.Owner;
import com.solvd.smarthome.domain.Room;
import com.solvd.smarthome.domain.SmartHome;
import com.solvd.smarthome.service.impl.OwnerServiceImpl;
import com.solvd.smarthome.service.impl.RoomServiceImpl;
import com.solvd.smarthome.service.impl.SmartHomeServiceImpl;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class RoomServiceTest {

    private RoomService roomService;
    private SmartHomeService smartHomeService;
    private OwnerService ownerService;
    private Room testRoom;
    private Long smartHomeId;

    @BeforeClass
    public void beforeClass() {
        roomService = new RoomServiceImpl();
        smartHomeService = new SmartHomeServiceImpl();
        ownerService = new OwnerServiceImpl();

        Owner owner = new Owner();
        owner.setFirstName("Room");
        owner.setLastName("Tester");
        owner.setEmail("roomtest" + System.currentTimeMillis() + "@example.com");
        ownerService.create(owner);

        SmartHome smartHome = new SmartHome();
        smartHome.setAddress("Test Address");
        smartHome.setOwnerId(owner.getId());
        smartHomeService.create(smartHome);
        smartHomeId = smartHome.getId();
    }

    @BeforeMethod
    public void beforeMethod() {
        testRoom = new Room();
        testRoom.setName("Test Room");
        testRoom.setAreaSqMeters(20.0);
        testRoom.setSmartHomeId(smartHomeId);
    }

    @Test
    public void testCreateRoom() {
        roomService.create(testRoom);
        Assert.assertNotNull(testRoom.getId(), "Room ID should not be null after creation");
    }

    @Test
    public void testFindRoomById() {
        roomService.create(testRoom);
        Room found = roomService.findById(testRoom.getId());
        Assert.assertEquals(found.getName(), testRoom.getName(), "Room name should match");
    }

    @Test
    public void testFindAllRooms() {
        roomService.create(testRoom);
        List<Room> rooms = roomService.findAll();
        Assert.assertTrue(rooms.size() > 0, "Room list should not be empty");
    }

    @Test
    public void testUpdateRoom() {
        roomService.create(testRoom);
        testRoom.setName("Updated Room");
        roomService.update(testRoom);
        Room updated = roomService.findById(testRoom.getId());
        Assert.assertEquals(updated.getName(), "Updated Room", "Room name should be updated");
    }

    @Test
    public void testDeleteRoom() {
        roomService.create(testRoom);
        Long id = testRoom.getId();
        roomService.delete(id);
        Room deleted = roomService.findById(id);
        Assert.assertNull(deleted, "Room should be null after deletion");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("RoomServiceTest finished");
    }
}