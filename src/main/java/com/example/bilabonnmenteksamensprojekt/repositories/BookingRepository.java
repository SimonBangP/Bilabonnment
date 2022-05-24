package com.example.bilabonnmenteksamensprojekt.repositories;

import com.example.bilabonnmenteksamensprojekt.models.bookings.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookingRepository {

    @Autowired
    JdbcTemplate template;

public List<Booking> getBookings (){
    return null;
}
public void createNewBooking (Booking booking){
}

public Booking findspecificBooking (int bookingId){

    Booking booking = null;
    return booking;
}


}
