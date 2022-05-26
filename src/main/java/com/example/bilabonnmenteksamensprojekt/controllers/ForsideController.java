package com.example.bilabonnmenteksamensprojekt.controllers;

import com.example.bilabonnmenteksamensprojekt.models.cars.Car;
import com.example.bilabonnmenteksamensprojekt.models.tickets.Ticket;
import com.example.bilabonnmenteksamensprojekt.services.TicketService;
import com.example.bilabonnmenteksamensprojekt.services.cars.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ForsideController {

    @Autowired
    TicketService ticketService;

    @Autowired
    CarService carService;

    @GetMapping ("/forside")
    public String forside(HttpSession session, Model model){
        if (session.getAttribute("authenticated") != null &&((boolean) session.getAttribute("authenticated"))) {

            // Add session user data to the model
            model.addAttribute("userFirstname", (String)session.getAttribute("userFirstname"));
            model.addAttribute("userLastnameChar", ((String)session.getAttribute("userLastname")).charAt(0));

            // Add ticket data to the model
            List<Ticket> ticketList = ticketService.getTickets();
            model.addAttribute("tickets", ticketList);

            //Add cars in active booking
            int usedCarAmount = carService.getUsedCarsAmount();
            model.addAttribute("usedCarAmount", usedCarAmount);

            // Add cars thats in storage
            List<Car> carsInStorage = carService.getCarsInStorage();
            model.addAttribute("cars", carsInStorage);


            // Find Total amount on cars that are rented.
            int carsPriceAmount = carService.getTotalPrice();
            model.addAttribute("totalPrice", carsPriceAmount);

            return "forside";
        }
        else {
            return "redirect:/?location=forside";
        }
    }
}
