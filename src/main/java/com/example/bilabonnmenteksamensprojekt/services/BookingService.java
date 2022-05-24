package com.example.bilabonnmenteksamensprojekt.services;

import com.example.bilabonnmenteksamensprojekt.models.bookings.Booking;
import com.example.bilabonnmenteksamensprojekt.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    public List<Booking> getBookings (){
        return bookingRepository.getBookings();
    }

    public void createNewBooking (Booking booking){
        //bookingRepository.createNewBooking(booking);
    }
}
