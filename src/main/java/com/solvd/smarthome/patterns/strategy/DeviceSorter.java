package com.solvd.smarthome.patterns.strategy;

import com.solvd.smarthome.domain.Device;
import java.util.List;

public class DeviceSorter {

    private DeviceSortStrategy strategy;

    public DeviceSorter(DeviceSortStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(DeviceSortStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Device> sort(List<Device> devices) {
        return strategy.sort(devices);
    }
}
