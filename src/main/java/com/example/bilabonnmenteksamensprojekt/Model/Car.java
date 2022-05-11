package com.example.bilabonnmenteksamensprojekt.Model;

import java.util.Date;

public class Car {
    private String registrationNumber;
    private String carType;
    private String brand;
    private String model;
    private String fuelType;
    private double fuelConsumption;
    private int odometer;
    private double engineSize;
    private int horsePower;
    private Date firstRegDate;

    public Car(String registrationNumber, String carType, String brand, String model, String fuelType, double fuelConsumption, int odometer, double engineSize, int horsePower, Date firstRegDate) {
        this.registrationNumber = registrationNumber;
        this.carType = carType;
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
        this.fuelConsumption = fuelConsumption;
        this.odometer = odometer;
        this.engineSize = engineSize;
        this.horsePower = horsePower;
        this.firstRegDate = firstRegDate;
    }
    public Car(){}
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(double engineSize) {
        this.engineSize = engineSize;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public Date getFirstRegDate() {
        return firstRegDate;
    }

    public void setFirstRegDate(Date firstRegDate) {
        this.firstRegDate = firstRegDate;
    }
}
