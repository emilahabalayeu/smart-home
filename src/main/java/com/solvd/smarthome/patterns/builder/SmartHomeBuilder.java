package com.solvd.smarthome.patterns.builder;

import com.solvd.smarthome.domain.Owner;
import com.solvd.smarthome.domain.Room;
import com.solvd.smarthome.domain.SmartHome;

import java.util.ArrayList;
import java.util.List;

public class SmartHomeBuilder {

    private String address;
    private Owner owner;
    private List<Room> rooms = new ArrayList<>();

    public SmartHomeBuilder address(String address) {
        this.address = address;
        return this;
    }

    public SmartHomeBuilder owner(Owner owner) {
        this.owner = owner;
        return this;
    }

    public SmartHomeBuilder addRoom(Room room) {
        this.rooms.add(room);
        return this;
    }

    public SmartHome build() {
        SmartHome smartHome = new SmartHome();
        smartHome.setAddress(address);
        smartHome.setOwner(owner);
        smartHome.setRooms(rooms);
        return smartHome;
    }
}
