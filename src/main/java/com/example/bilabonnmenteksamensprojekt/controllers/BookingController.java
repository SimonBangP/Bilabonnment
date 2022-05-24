package com.example.bilabonnmenteksamensprojekt.controllers;

import com.example.bilabonnmenteksamensprojekt.models.bookings.Booking;
import com.example.bilabonnmenteksamensprojekt.services.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BookingController {
    BookingService bookingService;

    @GetMapping("/bookings")
    public String bookings(Model model){
        List<Booking> bookinglist = bookingService.getBookings();
        model.addAttribute("bookings", bookinglist);
        return "bookings/bookings";
    }


}
