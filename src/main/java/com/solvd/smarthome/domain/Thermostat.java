package com.solvd.smarthome.domain;

public class Thermostat {
    private Long id;
    private double targetTemperature;
    private double currentTemperature;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getTargetTemperature() { return targetTemperature; }
    public void setTargetTemperature(double targetTemperature) { this.targetTemperature = targetTemperature; }

    public double getCurrentTemperature() { return currentTemperature; }
    public void setCurrentTemperature(double currentTemperature) { this.currentTemperature = currentTemperature; }

    public void performAction() {
        System.out.println("Thermostat is heating to " + targetTemperature + "°C");
    }
}
