package com.example.bilabonnmenteksamensprojekt.controllers.api;

import com.example.bilabonnmenteksamensprojekt.models.cars.Car;
import com.example.bilabonnmenteksamensprojekt.models.cars.CarEngine;
import com.example.bilabonnmenteksamensprojekt.models.cars.CarSpecification;
import com.example.bilabonnmenteksamensprojekt.services.cars.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cars")
public class CarsApi {

    @Autowired
    CarService carService;

    @Operation(summary = "Gets all cars", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404", content = @Content())})
    @GetMapping("/")
    public ResponseEntity<List<Car>> getAll() {
        List<Car> cars = carService.getCars();
        if (cars == null || cars.size() <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
    }

    @Operation(summary = "Gets a specific car", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404", content = @Content())})
    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable int id) {
        Car car = carService.getCarById(id);
        if (car == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(car, HttpStatus.OK);
        }
    }

    @Operation(summary = "Inserts a car", responses = {@ApiResponse(responseCode = "201")})
    @PostMapping("/")
    public ResponseEntity<Void> insert(@RequestParam(name = "GearType")CarEngine.GearType gearType, @RequestParam(name = "FuelType")CarEngine.FuelType fuelType,
                          @RequestParam(name = "Emissions")int emissions, @RequestParam(name = "KilometersPerLiter")int kilometersPerLiter,
                          @RequestParam(required = false, name = "EnginePower") int enginePower, @RequestParam(name = "Brand")String brand,
                          @RequestParam(name = "Model")String model, @RequestParam(name = "Variant")String variant, @RequestParam(name = "Color")String color,
                          @RequestParam(name = "Price")int price, @RequestParam(name = "insuranceIncluded")boolean insuranceIncluded,
                          @RequestParam(name = "OwnersFeeIncluded")boolean ownersFeeIncluded, @RequestParam(name = "Description")String description) {
        CarEngine engine = new CarEngine(enginePower, gearType, fuelType, emissions, kilometersPerLiter);
        CarSpecification spec = new CarSpecification(engine, brand, model, variant, color);
        Car car = new Car(spec, price, insuranceIncluded, ownersFeeIncluded, description);
        carService.insertCar(car);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Operation(summary = "Updates a car", responses = {@ApiResponse(responseCode = "200")})
    @PostMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestParam(name = "GearType")CarEngine.GearType gearType,
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
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "Removes a car", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        Car car = carService.getCarById(id);

        if (car == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        carService.removeCar(car);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
