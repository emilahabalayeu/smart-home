package com.solvd.smarthome.patterns.strategy;

import com.solvd.smarthome.domain.Device;
import java.util.Comparator;
import java.util.List;

public class SortByModel implements DeviceSortStrategy {

    @Override
    public List<Device> sort(List<Device> devices) {
        devices.sort(Comparator.comparing(Device::getModel));
        return devices;
    }
}
