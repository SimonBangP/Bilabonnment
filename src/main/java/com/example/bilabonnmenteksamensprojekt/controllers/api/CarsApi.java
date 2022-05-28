package com.example.bilabonnmenteksamensprojekt.controllers.api;

import com.example.bilabonnmenteksamensprojekt.models.cars.Car;
import com.example.bilabonnmenteksamensprojekt.models.cars.CarEngine;
import com.example.bilabonnmenteksamensprojekt.models.cars.CarSpecification;
import com.example.bilabonnmenteksamensprojekt.services.cars.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CarsApi {

    private final String version = "v1";
    private final String prefix = "cars";

    @Autowired
    CarService carService;

    @GetMapping(version + "/" + prefix + "/")
    public List<Car> getAll() {
        return carService.getCars();
    }

    @GetMapping(version + "/" + prefix + "/{id}")
    public Car getById(@PathVariable int id) {
        return carService.getCarById(id);
    }

    @PostMapping(version + "/" + prefix + "/")
    public void insert(@RequestParam(name = "GearType")CarEngine.GearType gearType, @RequestParam(name = "FuelType")CarEngine.FuelType fuelType,
                          @RequestParam(name = "Emissions")int emissions, @RequestParam(name = "KilometersPerLiter")int kilometersPerLiter,
                          @RequestParam(required = false, name = "EnginePower") int enginePower, @RequestParam(name = "Brand")String brand,
                          @RequestParam(name = "Model")String model, @RequestParam(name = "Variant")String variant, @RequestParam(name = "Color")String color,
                          @RequestParam(name = "Price")int price, @RequestParam(name = "insuranceIncluded")boolean insuranceIncluded,
                          @RequestParam(name = "OwnersFeeIncluded")boolean ownersFeeIncluded, @RequestParam(name = "Description")String description) {
        CarEngine engine = new CarEngine(enginePower, gearType, fuelType, emissions, kilometersPerLiter);
        CarSpecification spec = new CarSpecification(engine, brand, model, variant, color);
        Car car = new Car(spec, price, insuranceIncluded, ownersFeeIncluded, description);
        carService.insertCar(car);
    }

    @PostMapping(version + "/" + prefix + "/{id}")
    public void update(@PathVariable int id, @RequestParam(name = "GearType")CarEngine.GearType gearType,
                          @RequestParam(name = "FuelType")CarEngine.FuelType fuelType,
                          @RequestParam(name = "Emissions")int emissions, @RequestParam(name = "KilometersPerLiter")int kilometersPerLiter,
                          @RequestParam(required = false, name = "EnginePower") int enginePower, @RequestParam(name = "Brand")String brand,
                          @RequestParam(name = "Model")String model, @RequestParam(name = "Variant")String variant, @RequestParam(name = "Color")String color,
                          @RequestParam(name = "Price")int price, @RequestParam(name = "insuranceIncluded")boolean insuranceIncluded,
                          @RequestParam(name = "OwnersFeeIncluded")boolean ownersFeeIncluded, @RequestParam(name = "Description")String description) {
        CarEngine engine = new CarEngine(enginePower, gearType, fuelType, emissions, kilometersPerLiter);
        CarSpecification spec = new CarSpecification(engine, brand, model, variant, color);
        Car car = new Car(spec, price, insuranceIncluded, ownersFeeIncluded, description);
        carService.updateCarById(id, car);
    }
}
