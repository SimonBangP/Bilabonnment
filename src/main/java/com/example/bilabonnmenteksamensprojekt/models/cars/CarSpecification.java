package com.example.bilabonnmenteksamensprojekt.models.cars;

public class CarSpecification {

    private int SpecificationId;
    private CarModel carModel;
    private CarEngine carEngine;
    private String variant;
    private String color;

    public CarSpecification(int specificationId, CarModel carModel, CarEngine carEngine, String variant, String color) {
        SpecificationId = specificationId;
        this.carModel = carModel;
        this.carEngine = carEngine;
        this.variant = variant;
        this.color = color;
    }

    public CarSpecification(CarModel carModel, CarEngine carEngine, String variant, String color) {
        this.carModel = carModel;
        this.carEngine = carEngine;
        this.variant = variant;
        this.color = color;
    }

    public int getSpecificationId() {
        return SpecificationId;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
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
}
