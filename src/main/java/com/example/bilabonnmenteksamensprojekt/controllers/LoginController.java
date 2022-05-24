package com.example.bilabonnmenteksamensprojekt.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.bilabonnmenteksamensprojekt.services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    UserAuthenticationService authenticationService;

    @GetMapping("/login")
    public RedirectView tryLogin(HttpSession session, @RequestParam String username, @RequestParam String password, @RequestParam String location) {
        if (authenticationService.authenticateUser(username, password)) {
            session.setAttribute("authenticated", true);
            return new RedirectView("/forside" + location);
        }
        else {
            session.setAttribute("authenticated", false);
            return new RedirectView("/?nologin=true");
        }
    }
}
