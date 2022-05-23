package com.example.bilabonnmenteksamensprojekt.models.cars;

public class Car {

    private int carId;
    private CarSpecification carSpecification;
    private double price;
    private boolean insuranceIncluded;
    private boolean ownersFeeIncluded;
    private String shortDescription;
    private String status;

    public Car() {

    }

    public Car(int carId, CarSpecification carSpecification, double price, boolean insurranceIncluded, boolean ownersFeeIncluded, String shortDescription) {
        this.carId = carId;
        this.carSpecification = carSpecification;
        this.price = price;
        this.insuranceIncluded = insurranceIncluded;
        this.ownersFeeIncluded = ownersFeeIncluded;
        this.shortDescription = shortDescription;
    }

    public Car(CarSpecification carSpecification, double price, boolean insurranceIncluded, boolean ownersFeeIncluded, String shortDescription) {
        this.carSpecification = carSpecification;
        this.price = price;
        this.insuranceIncluded = insurranceIncluded;
        this.ownersFeeIncluded = ownersFeeIncluded;
        this.shortDescription = shortDescription;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public CarSpecification getCarSpecification() {
        return carSpecification;
    }

    public void setCarSpecification(CarSpecification carSpecification) {
        this.carSpecification = carSpecification;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isInsuranceIncluded() {
        return insuranceIncluded;
    }

    public void setInsuranceIncluded(boolean insuranceIncluded) {
        this.insuranceIncluded = insuranceIncluded;
    }

    public boolean isOwnersFeeIncluded() {
        return ownersFeeIncluded;
    }

    public void setOwnersFeeIncluded(boolean ownersFeeIncluded) {
        this.ownersFeeIncluded = ownersFeeIncluded;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getStatus() {
        return status;
    }
}
