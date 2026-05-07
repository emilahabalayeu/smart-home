package com.solvd.smarthome;

import java.time.LocalDateTime;

public class Device {
    private Long id;
    private String name;
    private String model;
    private boolean isOnline;
    private LocalDateTime installedAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public boolean isOnline() { return isOnline; }
    public void setOnline(boolean online) { isOnline = online; }

    public LocalDateTime getInstalledAt() { return installedAt; }
    public void setInstalledAt(LocalDateTime installedAt) { this.installedAt = installedAt; }
}
