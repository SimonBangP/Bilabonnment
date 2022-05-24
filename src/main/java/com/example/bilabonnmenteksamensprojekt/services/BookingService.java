package com.example.bilabonnmenteksamensprojekt.services;

import com.example.bilabonnmenteksamensprojekt.models.bookings.Booking;
import com.example.bilabonnmenteksamensprojekt.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    public List<Booking> getBookings (){
        return bookingRepository.getBookings();
    }


    public Booking getBookingById (int bookingId){
   return bookingRepository.getBookingById(bookingId);
    }
}
