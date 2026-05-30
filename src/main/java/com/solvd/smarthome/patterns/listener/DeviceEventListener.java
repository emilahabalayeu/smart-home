package com.solvd.smarthome.patterns.listener;

import com.solvd.smarthome.domain.Device;

public interface DeviceEventListener {
    void onDeviceOnline(Device device);
    void onDeviceOffline(Device device);
}
