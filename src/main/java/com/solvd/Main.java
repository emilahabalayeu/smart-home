package com.solvd;

import com.solvd.smarthome.domain.Owner;
import com.solvd.smarthome.domain.SmartHome;
import com.solvd.smarthome.domain.Room;
import com.solvd.smarthome.service.OwnerService;
import com.solvd.smarthome.service.SmartHomeService;
import com.solvd.smarthome.service.RoomService;
import com.solvd.smarthome.service.impl.OwnerServiceImpl;
import com.solvd.smarthome.service.impl.SmartHomeServiceImpl;
import com.solvd.smarthome.service.impl.RoomServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        OwnerService ownerService = new OwnerServiceImpl();
        SmartHomeService smartHomeService = new SmartHomeServiceImpl();
        RoomService roomService = new RoomServiceImpl();

        // Получить всех владельцев из БД
        List<Owner> owners = ownerService.findAll();
        owners.forEach(o -> System.out.println("Owner: " + o.getFirstName() + " " + o.getLastName()));

        // Получить все дома из БД
        List<SmartHome> homes = smartHomeService.findAll();
        homes.forEach(h -> System.out.println("Home: " + h.getAddress()));

        // Получить все комнаты из БД
        List<Room> rooms = roomService.findAll();
        rooms.forEach(r -> System.out.println("Room: " + r.getName()));
    }
}