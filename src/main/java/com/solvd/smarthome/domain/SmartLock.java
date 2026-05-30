package com.solvd.smarthome.domain;

public class SmartLock {
    private Long id;
    private boolean isLocked;
    private String accessCode;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public boolean isLocked() { return isLocked; }
    public void setLocked(boolean locked) { isLocked = locked; }

    public String getAccessCode() { return accessCode; }
    public void setAccessCode(String accessCode) { this.accessCode = accessCode; }
}
