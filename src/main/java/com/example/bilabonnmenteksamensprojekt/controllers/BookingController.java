package com.example.bilabonnmenteksamensprojekt.controllers;

import com.example.bilabonnmenteksamensprojekt.models.bookings.Booking;
import com.example.bilabonnmenteksamensprojekt.models.cars.Car;
import com.example.bilabonnmenteksamensprojekt.models.customers.Customer;
import com.example.bilabonnmenteksamensprojekt.models.locations.Location;
import com.example.bilabonnmenteksamensprojekt.models.users.Rights;
import com.example.bilabonnmenteksamensprojekt.services.BookingService;
import com.example.bilabonnmenteksamensprojekt.services.CustomerService;
import com.example.bilabonnmenteksamensprojekt.services.LocationsService;
import com.example.bilabonnmenteksamensprojekt.services.cars.CarService;
import com.example.bilabonnmenteksamensprojekt.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BookingController {

    @Autowired
    BookingService bookingService;

    @Autowired
    UserService authenticationService;

    @Autowired
    CustomerService customerService;

    @Autowired
    CarService carService;

    @Autowired
    LocationsService locationsService;

    @GetMapping("/bookings")
    public String viewBookings(HttpSession session, Model model){
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
            if (authenticationService.userHasRight((String)session.getAttribute("authorizedUsername"), Rights.ViewBookingDetails)) {
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

    @GetMapping("/createBooking")
    public String createBooking(Model model) {
        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customers", customers);

        List<Car> cars = carService.getCars();
        model.addAttribute("cars", cars);

        List<Location> locations = locationsService.getPickupLocations();
        model.addAttribute("locations", locations);

        return "bookings/createBooking";
    }
}
