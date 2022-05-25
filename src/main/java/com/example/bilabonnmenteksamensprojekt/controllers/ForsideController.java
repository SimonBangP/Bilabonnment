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
            model.addAttribute("userFirstname", (String)session.getAttribute("userFirstname"));
            model.addAttribute("userLastnameChar", ((String)session.getAttribute("userLastname")).charAt(0));
            return "forside";
        }
        else {
            return "redirect:/?location=forside";
        }
    }
}
