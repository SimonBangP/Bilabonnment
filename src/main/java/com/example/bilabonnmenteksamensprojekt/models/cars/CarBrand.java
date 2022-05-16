package com.example.bilabonnmenteksamensprojekt.models.cars;

public class CarBrand {

    private int brandId;
    private String Brand;

    public CarBrand(int brandId, String brand) {
        this.brandId = brandId;
        Brand = brand;
    }

    public CarBrand(String brand) {
        Brand = brand;
    }

    public int getBrandId() {
        return brandId;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }
}
