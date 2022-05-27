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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String createTickets (HttpSession session){
        if (session.getAttribute("authenticated") != null &&((boolean) session.getAttribute("authenticated"))) {

            return "/tickets/createTickets";
        }
        else {
            return "redirect:/?location=forside";
        }
    }

    @PostMapping ("/createTickets")
    public String createTickets (@ModelAttribute Ticket ticket){
        ticketService.createTicket(ticket);
        return "redirect:/tickets";

    }

}
