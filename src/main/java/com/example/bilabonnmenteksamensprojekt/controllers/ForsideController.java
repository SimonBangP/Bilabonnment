package com.example.bilabonnmenteksamensprojekt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForsideController {

    @GetMapping ("/forside")
    public String forside(){
        return "forside";
    }
}