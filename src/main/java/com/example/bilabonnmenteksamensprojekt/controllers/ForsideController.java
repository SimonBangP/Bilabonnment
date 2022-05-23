package com.example.bilabonnmenteksamensprojekt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ForsideController {

    @GetMapping ("/forside")
    public String forside(HttpSession session, Model model){
        if (session.getAttribute("authenticated") != null &&((boolean) session.getAttribute("authenticated"))) {
            return "forside";
        }
        else {
            return "redirect:/?location=forside";
        }
    }
}
