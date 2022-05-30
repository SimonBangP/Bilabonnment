package com.example.bilabonnmenteksamensprojekt.controllers;

import com.example.bilabonnmenteksamensprojekt.models.users.User;
import com.example.bilabonnmenteksamensprojekt.services.users.UserService;
import com.example.bilabonnmenteksamensprojekt.services.users.RightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SettingsController {

    @Autowired
    UserService userService;


    @GetMapping ("/settings")
    public String viewTickets (HttpSession session, Model model){
        if (session.getAttribute("authenticated") != null &&((boolean) session.getAttribute("authenticated"))) {
            List<User> ticketList = userService.getUsers();
            model.addAttribute("users", ticketList);
            return "/settings";
        }
        else {
            return "redirect:/?location=forside";
        }
    }

}
