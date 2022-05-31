package com.example.bilabonnmenteksamensprojekt.controllers;


import com.example.bilabonnmenteksamensprojekt.models.cars.Car;
import com.example.bilabonnmenteksamensprojekt.models.users.Rights;
import com.example.bilabonnmenteksamensprojekt.services.users.UserService;
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
    UserService authenticationService;

    @GetMapping("/cars")
    public String viewCars(HttpSession session, Model model){
        if (session.getAttribute("authenticated") != null &&((boolean) session.getAttribute("authenticated"))) {
            List<Car> cars = carService.getCars();

            model.addAttribute("cars", cars);
            return "cars/cars";
        }
        else {
            return "redirect:/?location=cars";
        }
    }

    @GetMapping("/cars/{carId}")
    public String getCarDetails(HttpSession session, Model model, @PathVariable Integer carId) {
        if (session.getAttribute("authenticated") != null && ((boolean) session.getAttribute("authenticated"))) {
            if (authenticationService.userHasRight((String)session.getAttribute("authorizedUsername"), Rights.ViewCarDetails)) {
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


        return "cars/cars";
    }

    @GetMapping("/cars/used")
    public String  getUsedCars  (Model model){
        int usedCars = carService.getUsedCarsAmount();
        model.addAttribute("usedCars", usedCars);
        return "cars/cars";
    }
}
