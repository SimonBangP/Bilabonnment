package com.example.bilabonnmenteksamensprojekt.controllers;

import com.example.bilabonnmenteksamensprojekt.models.users.User;
import com.example.bilabonnmenteksamensprojekt.models.users.UserRight;
import com.example.bilabonnmenteksamensprojekt.models.system.alarms.Alarm;
import com.example.bilabonnmenteksamensprojekt.services.users.RightsService;
import com.example.bilabonnmenteksamensprojekt.services.users.UserService;
import com.example.bilabonnmenteksamensprojekt.services.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SettingsController {

    @Autowired
    UserService userService;

    @Autowired
    RightsService rightsService;

    @Autowired
    AlarmService alarmService;

    @GetMapping ("/settings")
    public String viewTickets (HttpSession session, Model model){
        if (session.getAttribute("authenticated") != null &&((boolean) session.getAttribute("authenticated"))) {
            List<User> ticketList = userService.getUsers();
            model.addAttribute("users", ticketList);
            return "/settings/settings";
        }
        else {
            return "redirect:/?location=forside";
        }
    }

    @GetMapping ("/settings/changeuserright/{userID}")
    public String editTicket (HttpSession session, Model model, @PathVariable Integer userID){
        if (session.getAttribute("authenticated") != null &&((boolean) session.getAttribute("authenticated"))) {
           // List<UserRight> ticketList = rightsService.;
           // model.addAttribute("users", ticketList);
            return "/settings/settings";
        }
        else {
            return "redirect:/?location=forside";
        }
    }

    @GetMapping ("/settings/alarms")
    public String alarmSettings (HttpSession session, Model model){
        if (session.getAttribute("authenticated") != null &&((boolean) session.getAttribute("authenticated"))) {
            List<User> ticketList = userService.getUsers();
            List<Alarm> alarmList = alarmService.getAllAlarms();
            model.addAttribute("users", ticketList);
            model.addAttribute("alarms", alarmList);
            return "/settings/alarms";
        }
        else {
            return "redirect:/?location=forside";
        }
    }
}
