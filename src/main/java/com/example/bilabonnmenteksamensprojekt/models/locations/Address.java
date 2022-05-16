package com.example.bilabonnmenteksamensprojekt.models.locations;

public class Address {

    private int addressId;
    private String street;
    private int houseNumber;
    private ZipCode zipCode;

    public Address(int addressId, String street, int houseNumber, ZipCode zipCode) {
        this.addressId = addressId;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
    }

    public Address(String street, int houseNumber, ZipCode zipCode) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
    }

    public int getAddressId() {
        return addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public ZipCode getZipCode() {
        return zipCode;
    }

    public void setZipCode(ZipCode zipCode) {
        this.zipCode = zipCode;
    }
}
