package com.example.bilabonnmenteksamensprojekt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.bilabonnmenteksamensprojekt.models.tickets.Ticket;
import com.example.bilabonnmenteksamensprojekt.services.TicketService;
import javax.servlet.http.HttpSession;
import java.util.List;
import com.example.bilabonnmenteksamensprojekt.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
}
