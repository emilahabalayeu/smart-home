package com.solvd.smarthome.patterns.strategy;

import com.solvd.smarthome.domain.Device;
import java.util.List;

public interface DeviceSortStrategy {
    List<Device> sort(List<Device> devices);
}
