package com.example.bilabonnmenteksamensprojekt.controllers;

import com.example.bilabonnmenteksamensprojekt.models.tickets.Tickets;
import com.example.bilabonnmenteksamensprojekt.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

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



    @Autowired
    TicketService ticketService;
    @GetMapping ("forside/tickets")
    public String viewTickets (HttpSession session, Model model){
        if (session.getAttribute("authenticated") != null &&((boolean) session.getAttribute("authenticated"))) {
            List<Tickets> ticketList = ticketService.getTickets();
            model.addAttribute("tickets", ticketList);
            return "/forside";
        }
        else {
            return "redirect:/?location=forside";
        }

    }

}
