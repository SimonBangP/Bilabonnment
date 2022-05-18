package com.example.bilabonnmenteksamensprojekt.controllers;


import com.example.bilabonnmenteksamensprojekt.models.cars.Car;
import com.example.bilabonnmenteksamensprojekt.services.cars.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    CarService carService;


    @GetMapping("/cars")
    public String car (Model model){
        List<Car> carList = carService.getCars();
        model.addAttribute("cars", carList);
        return "cars/cars";
    }

    @GetMapping("/cars/{carId}")
    public String getCarDetails(Model model, @PathVariable Integer carId) {
        Car car = carService.getCarById(carId);
        model.addAttribute("car", car);
        return "cars/carDetails";
    }
}
