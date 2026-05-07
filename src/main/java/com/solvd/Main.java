package com.solvd;

import com.solvd.smarthome.*;

public class Main {
    public static void main(String[] args) {
        Owner owner = new Owner();
        owner.setFirstName("John");
        owner.setLastName("Doe");
        owner.setEmail("john@example.com");

        SmartHome home = new SmartHome();
        home.setAddress("123 Main St");
        home.setOwner(owner);

        Room livingRoom = new Room();
        livingRoom.setName("Living Room");
        livingRoom.setAreaSqMeters(25.5);

        Thermostat thermostat = new Thermostat();
        thermostat.setTargetTemperature(22.0);
        thermostat.performAction();
    }
}