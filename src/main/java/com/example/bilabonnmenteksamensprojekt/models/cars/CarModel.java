package com.example.bilabonnmenteksamensprojekt.models.cars;

public class CarModel {

    private int modelId;
    private CarBrand brand;
    private String Model;

    public CarModel(int modelId, CarBrand brand, String model) {
        this.modelId = modelId;
        this.brand = brand;
        Model = model;
    }

    public CarModel(CarBrand brand, String model) {
        this.brand = brand;
        Model = model;
    }

    public int getModelId() {
        return modelId;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public void setBrand(CarBrand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }
}
