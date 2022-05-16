package com.example.bilabonnmenteksamensprojekt.models.cars;

public class CarSpecification {

    private int SpecificationId;
    private CarEngine carEngine;
    private String brand;
    private String model;
    private String variant;
    private String color;

    public CarSpecification(int specificationId, CarEngine carEngine, String brand, String model, String variant, String color) {
        SpecificationId = specificationId;
        this.carEngine = carEngine;
        this.brand = brand;
        this.model = model;
        this.variant = variant;
        this.color = color;
    }

    public CarSpecification(CarEngine carEngine, String brand, String model, String variant, String color) {
        this.carEngine = carEngine;
        this.brand = brand;
        this.model = model;
        this.variant = variant;
        this.color = color;
    }

    public int getSpecificationId() {
        return SpecificationId;
    }

    public CarEngine getCarEngine() {
        return carEngine;
    }

    public void setCarEngine(CarEngine carEngine) {
        this.carEngine = carEngine;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
}
