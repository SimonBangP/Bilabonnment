package com.example.bilabonnmenteksamensprojekt.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarController {


    @GetMapping("/cars")
    public String car (Model model){
        return "cars/cars";
    }

}
