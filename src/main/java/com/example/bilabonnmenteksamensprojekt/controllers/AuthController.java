package com.example.bilabonnmenteksamensprojekt.controllers;

import javax.servlet.http.HttpSession;

import com.example.bilabonnmenteksamensprojekt.models.users.User;
import com.example.bilabonnmenteksamensprojekt.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthController {

    @Autowired
    UserService authenticationService;

    @GetMapping("/login")
    public RedirectView tryLogin(HttpSession session, @RequestParam String username, @RequestParam String password, @RequestParam String location) {
        if (authenticationService.authenticateUser(username, password)) {
            User user = authenticationService.getUserByUsername(username);
            session.setAttribute("userFirstname", user.getFirstName());
            session.setAttribute("userLastname", user.getLastName());
            session.setAttribute("authorizedUsername", username);
            session.setAttribute("userData", user);
            session.setAttribute("authenticated", true);

            return new RedirectView("/navigation?location=" + location);
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

    @GetMapping("/changePassword")
    public String changeUserpassword(HttpSession session, Model model) {
        User user = (User)session.getAttribute("userData");
        model.addAttribute("userId", user.getUserId());
        return "settings/changePassword";
    }
}
