package com.example.bilabonnmenteksamensprojekt.models.cars;

import com.example.bilabonnmenteksamensprojekt.models.customers.Customer;
import org.springframework.beans.factory.annotation.Autowired;

public class Car {

    private int carId;
    private CarSpecification carSpecification;
    private double price;
    private boolean insuranceIncluded;
    private boolean ownersFeeIncluded;
    private String shortDescription;
    private Customer customer;
    private String status;

    public Car() {

    }

    public Car(int carId, CarSpecification carSpecification, double price, boolean insurranceIncluded, boolean ownersFeeIncluded, String shortDescription, Customer customer) {
        this.carId = carId;
        this.carSpecification = carSpecification;
        this.price = price;
        this.insuranceIncluded = insurranceIncluded;
        this.ownersFeeIncluded = ownersFeeIncluded;
        this.shortDescription = shortDescription;
        this.customer = customer;
    }

    public Car(CarSpecification carSpecification, double price, boolean insurranceIncluded, boolean ownersFeeIncluded, String shortDescription, Customer customer) {
        this.carSpecification = carSpecification;
        this.price = price;
        this.insuranceIncluded = insurranceIncluded;
        this.ownersFeeIncluded = ownersFeeIncluded;
        this.shortDescription = shortDescription;
        this.customer = customer;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getAllInfoToString(){
        return(carSpecification.getBrand() + ", " + carSpecification.getModel() + ", " + carSpecification.getVariant() + ", " + status);
    }

}
