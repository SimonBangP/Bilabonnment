package com.example.bilabonnmenteksamensprojekt.models.locations;

public class Location {

    private int locationId;
    private String locationName;
    private Address address;

    public Location(int locationId, String locationName, Address address) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.address = address;
    }

    public Location(String locationName, Address address) {
        this.locationName = locationName;
        this.address = address;
    }

    public int getLocationId() {
        return locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
