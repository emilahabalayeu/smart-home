package com.solvd.smarthome.domain;

public class EnergyMeter {
    private Long id;
    private double currentUsageKw;
    private double totalUsageKwh;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getCurrentUsageKw() { return currentUsageKw; }
    public void setCurrentUsageKw(double currentUsageKw) { this.currentUsageKw = currentUsageKw; }

    public double getTotalUsageKwh() { return totalUsageKwh; }
    public void setTotalUsageKwh(double totalUsageKwh) { this.totalUsageKwh = totalUsageKwh; }
}
