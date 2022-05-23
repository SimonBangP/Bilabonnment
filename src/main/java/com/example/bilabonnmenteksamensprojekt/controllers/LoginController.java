package com.example.bilabonnmenteksamensprojekt.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;

@Controller
public class LoginController {

    @GetMapping("/login")
    public RedirectView tryLogin(@RequestParam String username, @RequestParam String password) {
        if (username.equals("admin") && password.equals("nejtak")) {
            return new RedirectView("/forside");
        }
        else {
            return new RedirectView("/nologin=true");
        }
    }
}
