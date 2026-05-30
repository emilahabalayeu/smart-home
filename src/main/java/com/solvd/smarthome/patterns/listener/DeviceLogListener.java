package com.solvd.smarthome.patterns.listener;

import com.solvd.smarthome.domain.Device;
import com.solvd.smarthome.domain.DeviceLog;
import com.solvd.smarthome.service.DeviceLogService;
import com.solvd.smarthome.service.impl.DeviceLogServiceImpl;

import java.time.LocalDateTime;

public class DeviceLogListener implements DeviceEventListener {

    private final DeviceLogService deviceLogService = new DeviceLogServiceImpl();

    @Override
    public void onDeviceOnline(Device device) {
        DeviceLog log = new DeviceLog();
        log.setDeviceId(device.getId());
        log.setMessage("Device online");
        log.setTimestamp(LocalDateTime.now());
        deviceLogService.create(log);
        System.out.println("Device " + device.getName() + " came online - logged");
    }

    @Override
    public void onDeviceOffline(Device device) {
        DeviceLog log = new DeviceLog();
        log.setDeviceId(device.getId());
        log.setMessage("Device offline");
        log.setTimestamp(LocalDateTime.now());
        deviceLogService.create(log);
        System.out.println("Device " + device.getName() + " went offline - logged");
    }
}
