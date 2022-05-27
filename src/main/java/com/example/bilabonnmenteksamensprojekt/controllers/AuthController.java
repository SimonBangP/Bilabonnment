package com.example.bilabonnmenteksamensprojekt.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.bilabonnmenteksamensprojekt.models.users.User;
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
public class AuthController {

    @Autowired
    UserAuthenticationService authenticationService;

    @GetMapping("/login")
    public RedirectView tryLogin(HttpSession session, @RequestParam String username, @RequestParam String password, @RequestParam String location) {
        if (authenticationService.authenticateUser(username, password)) {
            User user = authenticationService.getUserByUsername(username);
            session.setAttribute("userFirstname", user.getFirstName());
            session.setAttribute("userLastname", user.getLastName());
            session.setAttribute("authorizedUsername", username);
            session.setAttribute("authenticated", true);

            if (location.equals("")) {
                location = "navigation";
            }

            return new RedirectView("/" + location);
        }
        else {
            session.setAttribute("authenticated", false);
            return new RedirectView("/?nologin=true");
        }
    }

    @GetMapping("/logout")
    public RedirectView logout(HttpSession session) {
        session.invalidate();
        return new RedirectView("/");
    }

    @GetMapping("/authError")
    public String showAuthError() {
        return "autherror";
    }
}
