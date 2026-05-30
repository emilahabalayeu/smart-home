package com.solvd;

import com.solvd.smarthome.domain.Device;
import com.solvd.smarthome.domain.Owner;
import com.solvd.smarthome.domain.Room;
import com.solvd.smarthome.domain.SmartHome;
import com.solvd.smarthome.patterns.builder.SmartHomeBuilder;
import com.solvd.smarthome.patterns.decorator.LoggingDeviceService;
import com.solvd.smarthome.patterns.facade.SmartHomeFacade;
import com.solvd.smarthome.patterns.factory.BasicSmartHomeFactory;
import com.solvd.smarthome.patterns.factory.PremiumSmartHomeFactory;
import com.solvd.smarthome.patterns.strategy.DeviceSorter;
import com.solvd.smarthome.patterns.strategy.SortByName;
import com.solvd.smarthome.patterns.strategy.SortByOnlineStatus;
import com.solvd.smarthome.service.DeviceService;
import com.solvd.smarthome.service.impl.DeviceServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Factory
        System.out.println("=== Factory ===");
        BasicSmartHomeFactory basicFactory = new BasicSmartHomeFactory();
        Device basicDevice = basicFactory.createDevice("Thermostat", "T3");
        System.out.println("Basic device: " + basicDevice.getName());

        // Abstract Factory
        System.out.println("=== Abstract Factory ===");
        PremiumSmartHomeFactory premiumFactory = new PremiumSmartHomeFactory();
        Device premiumDevice = premiumFactory.createDevice("Thermostat", "T3");
        System.out.println("Premium device: " + premiumDevice.getName());

        // Builder
        System.out.println("=== Builder ===");
        Owner owner = new Owner();
        owner.setFirstName("John");
        owner.setLastName("Doe");
        SmartHome home = new SmartHomeBuilder()
                .address("123 Main St")
                .owner(owner)
                .build();
        System.out.println("Built home: " + home.getAddress());

        // Facade
        System.out.println("=== Facade ===");
        SmartHomeFacade facade = new SmartHomeFacade();
        List<SmartHome> homes = facade.getAllHomesWithDetails();
        homes.forEach(h -> System.out.println("Home via facade: " + h.getAddress()));

        // Decorator
        System.out.println("=== Decorator ===");
        DeviceService deviceService = new LoggingDeviceService(new DeviceServiceImpl());
        List<Device> devices = deviceService.findAll();

        // Strategy
        System.out.println("=== Strategy ===");
        DeviceSorter sorter = new DeviceSorter(new SortByName());
        List<Device> sortedByName = sorter.sort(devices);
        sortedByName.forEach(d -> System.out.println("Device: " + d.getName()));

        sorter.setStrategy(new SortByOnlineStatus());
        List<Device> sortedByStatus = sorter.sort(devices);
        sortedByStatus.forEach(d -> System.out.println("Device online=" + d.isOnline() + ": " + d.getName()));
    }
}
