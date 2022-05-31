package com.example.bilabonnmenteksamensprojekt.controllers.api;

import com.example.bilabonnmenteksamensprojekt.models.bookings.Booking;
import com.example.bilabonnmenteksamensprojekt.models.cars.Car;
import com.example.bilabonnmenteksamensprojekt.models.customers.Customer;
import com.example.bilabonnmenteksamensprojekt.models.locations.Location;
import com.example.bilabonnmenteksamensprojekt.models.system.Severity;
import com.example.bilabonnmenteksamensprojekt.models.system.alarms.Alarm;
import com.example.bilabonnmenteksamensprojekt.models.system.alarms.WatchCategory;
import com.example.bilabonnmenteksamensprojekt.models.users.User;
import com.example.bilabonnmenteksamensprojekt.services.*;
import com.example.bilabonnmenteksamensprojekt.services.cars.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("v1/bookings")
public class BookingsApi {

    @Autowired
    BookingService bookingService;

    @Autowired
    CustomerService customerService;

    @Autowired
    CarService service;

    @Autowired
    LocationsService locationsService;

    @Operation(summary = "Gets all bookings", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404", content = @Content())})
    @GetMapping("/")
    public ResponseEntity<List<Booking>> getAll() {
        List<Booking> bookings = bookingService.getBookings();
        if (bookings == null || bookings.size() <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        }
    }

    @Operation(summary = "Gets a specific booking", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404", content = @Content())})
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getById(@PathVariable int id) {
        Booking booking = bookingService.getBookingById(id);
        if (booking == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(booking, HttpStatus.OK);
        }
    }

    @Operation(summary = "Inserts a booking", responses = {@ApiResponse(responseCode = "201"), @ApiResponse(responseCode = "400")})
    @PostMapping("/createBooking")
    public ResponseEntity<Void> insert(@RequestParam(name = "CustomerId")int customerId, @RequestParam(name = "CarId")int carId,
                                 @RequestParam(name = "PickupLocationId")int locationId, @RequestParam(name = "DeliveryDate")Date deliveryDate,
                                 @RequestParam(name = "ReturnDate", required = false)Date returnDate) {
        Customer customer = customerService.getCustomerById(customerId);
        Car car = service.getCarById(carId);
        Location location = locationsService.getPickupLocationById(locationId);

        if (customer == null) {
            return new ResponseEntity("Customer with customerId: " + customerId + " not found", HttpStatus.BAD_REQUEST);
        }

        if (car == null) {
            return new ResponseEntity("Car with carId: " + carId + " not found", HttpStatus.BAD_REQUEST);
        }

        if (location == null) {
            return new ResponseEntity("Location with LocationId: " + locationId + " not found", HttpStatus.BAD_REQUEST);
        }

        Booking booking = new Booking(customer, car, location, deliveryDate, returnDate, false);

        bookingService.insertBooking(booking);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Operation(summary = "Updates a booking", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "400")})
    @PostMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestParam(name = "CustomerId")int customerId, @RequestParam(name = "CarId")int carId,
                                 @RequestParam(name = "PickupLocationId")int locationId, @RequestParam(name = "DeliveryDate")Date deliveryDate,
                                 @RequestParam(name = "ReturnDate", required = false)Date returnDate,
                                 @RequestParam(name = "Completed", required = false)boolean completed) {

        Customer customer = customerService.getCustomerById(customerId);
        Car car = service.getCarById(carId);
        Location location = locationsService.getPickupLocationById(locationId);

        if (customer == null) {
            return new ResponseEntity("Customer with customerId: " + customerId + " not found", HttpStatus.BAD_REQUEST);
        }

        if (car == null) {
            return new ResponseEntity("Car with carId: " + carId + " not found", HttpStatus.BAD_REQUEST);
        }

        if (location == null) {
            return new ResponseEntity("Location with LocationId: " + locationId + " not found", HttpStatus.BAD_REQUEST);
        }

        Booking booking = new Booking(customer, car, location, deliveryDate, returnDate, completed);

        bookingService.updateBooking(id, booking);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "Completes a booking", responses = {@ApiResponse(responseCode = "200")})
    @PostMapping(path = "/complete/{id}")
    public ResponseEntity<Void> complete(@PathVariable int id) {
        bookingService.endBooking(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
