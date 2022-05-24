package com.example.bilabonnmenteksamensprojekt.controllers;

import com.example.bilabonnmenteksamensprojekt.models.bookings.Booking;
import com.example.bilabonnmenteksamensprojekt.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping("/bookings")
    public String bookings(HttpSession session, Model model){
        if (session.getAttribute("authenticated") != null && ((boolean) session.getAttribute("authenticated"))) {
            List<Booking> bookinglist = bookingService.getBookings();
            model.addAttribute("bookings", bookinglist);
            return "bookings/bookings";
        }
        else {
            return "redirect:/?location=bookings";
        }
    }


}
