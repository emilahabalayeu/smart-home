package com.solvd.smarthome.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule {
    private Long id;
    private Long deviceId;
    private Device device;
    private LocalTime turnOnTime;
    private LocalTime turnOffTime;
    private LocalDate startDate;
    private boolean isActive;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getDeviceId() { return deviceId; }
    public void setDeviceId(Long deviceId) { this.deviceId = deviceId; }

    public Device getDevice() { return device; }
    public void setDevice(Device device) { this.device = device; }

    public LocalTime getTurnOnTime() { return turnOnTime; }
    public void setTurnOnTime(LocalTime turnOnTime) { this.turnOnTime = turnOnTime; }

    public LocalTime getTurnOffTime() { return turnOffTime; }
    public void setTurnOffTime(LocalTime turnOffTime) { this.turnOffTime = turnOffTime; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
}