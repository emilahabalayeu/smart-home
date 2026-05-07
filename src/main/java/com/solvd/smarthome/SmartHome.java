package com.solvd.smarthome;

import java.util.List;

public class SmartHome {
    private Long id;
    private String address;
    private Owner owner;
    private List<Room> rooms;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Owner getOwner() { return owner; }
    public void setOwner(Owner owner) { this.owner = owner; }

    public List<Room> getRooms() { return rooms; }
    public void setRooms(List<Room> rooms) { this.rooms = rooms; }
}
