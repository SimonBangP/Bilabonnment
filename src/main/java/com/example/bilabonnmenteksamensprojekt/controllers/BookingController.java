package com.example.bilabonnmenteksamensprojekt.controllers;

import com.example.bilabonnmenteksamensprojekt.models.bookings.Booking;
import com.example.bilabonnmenteksamensprojekt.services.BookingService;
import com.example.bilabonnmenteksamensprojekt.services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BookingController {

    @Autowired
    BookingService bookingService;

    @Autowired
    UserAuthenticationService authenticationService;

    @GetMapping("/bookings")
    public String bookings(HttpSession session, Model model){
        if (session.getAttribute("authenticated") != null && ((boolean) session.getAttribute("authenticated"))) {
            List<Booking> bookinglist = bookingService.getBookings();
            model.addAttribute("bookings", bookinglist);

            List<Booking> activeBookingList = bookingService.getActiveBookings();
            model.addAttribute("activeBookings", activeBookingList);
            return "bookings/bookings";
        } else {
            return "redirect:/?location=bookings";
        }
    }

    @GetMapping("/bookings/{bookingId}")
    public String getBookingDetails(HttpSession session, Model model, @PathVariable Integer bookingId) {
        if (session.getAttribute("authenticated") != null && ((boolean) session.getAttribute("authenticated"))) {
            if (authenticationService.userHasRight((String)session.getAttribute("authorizedUsername"), "ViewBookingDetails")) {
                Booking booking = bookingService.getBookingById(bookingId);
                model.addAttribute("booking", booking);
                return "bookings/bookingDetails";
            }
            else {
                return "redirect:/authError";
            }
        }
        else {
            return "redirect:/?location=booking/" + bookingId;
        }
    }

    @GetMapping("/endBooking/{bookingId}")
    public String endBooking(HttpSession session, @PathVariable Integer bookingId){
        Boolean ended = bookingService.endBooking(bookingId);
        if (session.getAttribute("authenticated") != null && ((boolean) session.getAttribute("authenticated"))) {
            if (ended) {
                return "redirect:/?location=bookings";
            } else {
                return "redirect:/?location=bookings";
            }
        }
        return "redirect:/?location=bookings";
    }
}
