package com.solvd.smarthome.domain;

import java.time.LocalDateTime;

public class DeviceLog {
    private Long id;
    private Long deviceId;
    private Device device;
    private String message;
    private LocalDateTime timestamp;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getDeviceId() { return deviceId; }
    public void setDeviceId(Long deviceId) { this.deviceId = deviceId; }

    public Device getDevice() { return device; }
    public void setDevice(Device device) { this.device = device; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}