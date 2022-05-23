package com.example.bilabonnmenteksamensprojekt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ForsideController {

    @GetMapping ("/forside")
    public String forside(HttpSession session){
        if (((boolean) session.getAttribute("authenticated"))) {
            return "forside";
        }
        else {
            return "redirect:/";
        }
    }
}
