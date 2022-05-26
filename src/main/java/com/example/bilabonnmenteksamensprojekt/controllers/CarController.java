package com.example.bilabonnmenteksamensprojekt.controllers;


import com.example.bilabonnmenteksamensprojekt.models.cars.Car;
import com.example.bilabonnmenteksamensprojekt.models.cars.CarEngine;
import com.example.bilabonnmenteksamensprojekt.models.cars.CarSpecification;
import com.example.bilabonnmenteksamensprojekt.services.UserAuthenticationService;
import com.example.bilabonnmenteksamensprojekt.services.cars.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CarController {

    @Autowired
    CarService carService;

    @Autowired
    UserAuthenticationService authenticationService;

    @GetMapping("/cars")
    public String viewCars(HttpSession session, Model model){
        if (session.getAttribute("authenticated") != null &&((boolean) session.getAttribute("authenticated"))) {
            List<Car> carList = carService.getCars();
            model.addAttribute("cars", carList);
            return "cars/cars";
        }
        else {
            return "redirect:/?location=cars";
        }
    }

    @GetMapping("/cars/{carId}")
    public String getCarDetails(HttpSession session, Model model, @PathVariable Integer carId) {
        if (session.getAttribute("authenticated") != null && ((boolean) session.getAttribute("authenticated"))) {
            if (authenticationService.userHasRight((String)session.getAttribute("authorizedUsername"), "ViewCarDetails")) {
                Car car = carService.getCarById(carId);
                model.addAttribute("car", car);
                return "cars/carDetails";
            }
            else {
                return "redirect:/authError";
            }
        }
        else {
            return "redirect:/?location=cars/" + carId;
        }
    }

    @GetMapping("cars/new")
    public String newRandomCar() {
        CarEngine engine = new CarEngine(450, CarEngine.GearType.Automatgear, CarEngine.FuelType.Diesel, 9852, 12);
        CarSpecification spec = new CarSpecification(engine, "Audi", "RSQ8", "", "Red");
        Car car = new Car(spec, 8542, false, false, "This is the story of a man named Stanley");
        carService.insertCar(car);

        return "cars/cars";
    }

    @GetMapping("/cars/used")
    public String  getUsedCars  (Model model){
        int usedCars = carService.getUsedCars();
        model.addAttribute("usedCars", usedCars);
        return "cars/cars";
    }
}
