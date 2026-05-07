package com.solvd.smarthome;

public class SmartLight {
    private Long id;
    private int brightness;
    private String color;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getBrightness() { return brightness; }
    public void setBrightness(int brightness) { this.brightness = brightness; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}
