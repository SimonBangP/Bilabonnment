package com.example.bilabonnmenteksamensprojekt.controllers;

import com.example.bilabonnmenteksamensprojekt.models.system.Severity;
import com.example.bilabonnmenteksamensprojekt.models.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.bilabonnmenteksamensprojekt.models.system.Ticket;
import com.example.bilabonnmenteksamensprojekt.services.TicketService;
import javax.servlet.http.HttpSession;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TicketController {

    @Autowired
    TicketService ticketService;


    @GetMapping ("/tickets")
    public String viewTickets (HttpSession session, Model model){
        if (session.getAttribute("authenticated") != null &&((boolean) session.getAttribute("authenticated"))) {
            List<Ticket> ticketList = ticketService.getTickets();
            model.addAttribute("tickets", ticketList);
            return "/tickets/tickets";
        }
        else {
            return "redirect:/?location=forside";
        }
    }

    @GetMapping ("/createTickets")
    public String createTickets (HttpSession session, Model model){
        if (session.getAttribute("authenticated") != null &&((boolean) session.getAttribute("authenticated"))) {
            User user = (User)session.getAttribute("userData");
            model.addAttribute("UserID", user.getUserId());
            return "/tickets/createTickets";
        }
        else {
            return "redirect:/?location=forside";
        }
    }

    @PostMapping ("/createTicket")
    public String createTickets (HttpSession session, @RequestParam("Severity")Severity severity, @RequestParam("Name")String name, @RequestParam("Description")String description){
        User currentUser = (User)session.getAttribute("userData");
        Ticket ticket = new Ticket(currentUser, severity, name, description);
        ticketService.insertTicket(ticket);
        return "redirect:/tickets";


    }

}
