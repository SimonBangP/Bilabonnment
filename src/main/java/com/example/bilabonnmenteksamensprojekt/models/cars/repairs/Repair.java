package com.example.bilabonnmenteksamensprojekt.models.cars.repairs;

import com.example.bilabonnmenteksamensprojekt.models.cars.Car;

import java.sql.Date;
import java.time.LocalDate;

public class Repair {

    private Car car;
    private Mechanic mechanic;
    private LocalDate startDate;
    private LocalDate expectedEndDate;
    private String repairDescription;

    public Repair(Car car, Mechanic mechanic, LocalDate startDate, LocalDate expectedEndDate, String repairDescription) {
        this.car = car;
        this.mechanic = mechanic;
        this.startDate = startDate;
        this.expectedEndDate = expectedEndDate;
        this.repairDescription = repairDescription;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getExpectedEndDate() {
        return expectedEndDate;
    }

    public void setExpectedEndDate(LocalDate expectedEndDate) {
        this.expectedEndDate = expectedEndDate;
    }

    public String getRepairDescription() {
        return repairDescription;
    }

    public void setRepairDescription(String repairDescription) {
        this.repairDescription = repairDescription;
    }
}
