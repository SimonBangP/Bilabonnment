package com.example.bilabonnmenteksamensprojekt;

import com.example.bilabonnmenteksamensprojekt.models.cars.Car;
import com.example.bilabonnmenteksamensprojekt.services.cars.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class RepositoryTests {

    @Autowired
    CarService carService;

    @Test
    public void testCarsRepository() {
        Car testCar = carService.getCarById(-1); // A test car with id -1 has been created in the database

        Assert.notNull(testCar, "Car not found");
        Assert.isTrue(!testCar.isInsuranceIncluded(), "Insurance is not false");
        Assert.isTrue(testCar.isOwnersFeeIncluded(), "Ownersfee is not true");
        Assert.isTrue(testCar.getPrice() == 17000, "Price is not correct, 17000 expected found: " + testCar.getPrice());
        Assert.isTrue(testCar.getShortDescription().equals("It iss wery kool"), "Short description is not correct \"It iss wery kool\" expected found: " + testCar.getShortDescription());

        Assert.notNull(testCar.getCarSpecification(), "Car specification not found");
        Assert.notNull(testCar.getCarSpecification().getCarEngine(), "Car engine not found");

    }

}
