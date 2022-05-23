package com.example.bilabonnmenteksamensprojekt.models.cars;

public class CarEngine {

    public enum GearType {

        Manuelt,
        Automatgear,
        Ingen

    }

    public enum FuelType {

        Benzin,
        Diesel,
        Hybrid,
        Elektrisk,
        Andet

    }

    private int engineId;
    private int enginePower;
    private GearType gearType;
    private FuelType fuelType;
    private int emissions;
    private double kilometersPerLiter;

    public CarEngine() {

    }

    public CarEngine(int engineId, int enginePower, GearType gearType, FuelType fuelType, int emissions, double kilometersPerLiter) {
        this.engineId = engineId;
        this.enginePower = enginePower;
        this.gearType = gearType;
        this.fuelType = fuelType;
        this.emissions = emissions;
        this.kilometersPerLiter = kilometersPerLiter;
    }

    public CarEngine(int enginePower, GearType gearType, FuelType fuelType, int emissions, double kilometersPerLiter) {
        this.enginePower = enginePower;
        this.gearType = gearType;
        this.fuelType = fuelType;
        this.emissions = emissions;
        this.kilometersPerLiter = kilometersPerLiter;
    }

    public int getEngineId() {
        return engineId;
    }

    public void setEngineId(int engineId) {
        this.engineId = engineId;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(int enginePower) {
        this.enginePower = enginePower;
    }

    public GearType getGearType() {
        return gearType;
    }

    public void setGearType(GearType gearType) {
        this.gearType = gearType;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public int getEmissions() {
        return emissions;
    }

    public void setEmissions(int emissions) {
        this.emissions = emissions;
    }

    public double getKilometersPerLiter() {
        return kilometersPerLiter;
    }

    public void setKilometersPerLiter(double kilometersPerLiter) {
        this.kilometersPerLiter = kilometersPerLiter;
    }
}
