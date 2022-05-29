package com.example.bilabonnmenteksamensprojekt.services;

import com.example.bilabonnmenteksamensprojekt.models.bookings.Booking;
import com.example.bilabonnmenteksamensprojekt.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService implements SearchableAlarmService{

    @Autowired
    BookingRepository repository;

    public List<Booking> getBookings (){
        return repository.getBookings();
    }

    public List<Booking> getActiveBookings (){
        return repository.getActiveBookings();
    }

    public Booking getBookingById (int bookingId){
        return repository.getBookingById(bookingId);
    }

    public void endBooking(int bookingId){
        repository.endBooking(bookingId);
    }

    public void insertBooking(Booking booking) {
        repository.insertBooking(booking);
    }

    public void updateBooking(int id, Booking booking) {
        repository.updateBooking(id, booking);
    }

    @Override
    public int getCount() {
        return repository.getCount();
    }

    @Override
    public int getCountWithWhereClause(String whereClause) {
        return repository.getCountWithWhereClause(whereClause);
    }
}