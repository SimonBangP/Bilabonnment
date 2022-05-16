package com.example.bilabonnmenteksamensprojekt.Controller;

import com.example.bilabonnmenteksamensprojekt.models.Car;
import com.example.bilabonnmenteksamensprojekt.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
