package com.example.bilabonnmenteksamensprojekt.models.cars;

public class Car {

    private int CarId;
    private CarSpecification carSpecification;
    private double price;
    private boolean insurranceIncluded;
    private boolean ownersFeeIncluded;
    private String ShortDescription;

    public Car(int carId, CarSpecification carSpecification, double price, boolean insurranceIncluded, boolean ownersFeeIncluded, String shortDescription) {
        CarId = carId;
        this.carSpecification = carSpecification;
        this.price = price;
        this.insurranceIncluded = insurranceIncluded;
        this.ownersFeeIncluded = ownersFeeIncluded;
        ShortDescription = shortDescription;
    }

    public Car(CarSpecification carSpecification, double price, boolean insurranceIncluded, boolean ownersFeeIncluded, String shortDescription) {
        this.carSpecification = carSpecification;
        this.price = price;
        this.insurranceIncluded = insurranceIncluded;
        this.ownersFeeIncluded = ownersFeeIncluded;
        ShortDescription = shortDescription;
    }

    public int getCarId() {
        return CarId;
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

    public boolean isInsurranceIncluded() {
        return insurranceIncluded;
    }

    public void setInsurranceIncluded(boolean insurranceIncluded) {
        this.insurranceIncluded = insurranceIncluded;
    }

    public boolean isOwnersFeeIncluded() {
        return ownersFeeIncluded;
    }

    public void setOwnersFeeIncluded(boolean ownersFeeIncluded) {
        this.ownersFeeIncluded = ownersFeeIncluded;
    }

    public String getShortDescription() {
        return ShortDescription;
    }

    public void setShortDescription(String shortDescription) {
        ShortDescription = shortDescription;
    }
}
