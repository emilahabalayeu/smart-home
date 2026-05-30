package com.solvd.smarthome.domain;

import java.util.List;

public class Room {
    private Long id;
    private String name;
    private double areaSqMeters;
    private Long smartHomeId;
    private List<Device> devices;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getAreaSqMeters() { return areaSqMeters; }
    public void setAreaSqMeters(double areaSqMeters) { this.areaSqMeters = areaSqMeters; }

    public Long getSmartHomeId() { return smartHomeId; }
    public void setSmartHomeId(Long smartHomeId) { this.smartHomeId = smartHomeId; }

    public List<Device> getDevices() { return devices; }
    public void setDevices(List<Device> devices) { this.devices = devices; }
}