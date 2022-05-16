package com.example.bilabonnmenteksamensprojekt.models.cars.repairs;

import com.example.bilabonnmenteksamensprojekt.models.locations.Address;

public class Mechanic {

    private int mechanicId;
    private String mechanicName;
    private Address address;

    public Mechanic(int mechanicId, String mechanicName, Address address) {
        this.mechanicId = mechanicId;
        this.mechanicName = mechanicName;
        this.address = address;
    }

    public Mechanic(String mechanicName, Address address) {
        this.mechanicName = mechanicName;
        this.address = address;
    }

    public int getMechanicId() {
        return mechanicId;
    }

    public String getMechanicName() {
        return mechanicName;
    }

    public void setMechanicName(String mechanicName) {
        this.mechanicName = mechanicName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
