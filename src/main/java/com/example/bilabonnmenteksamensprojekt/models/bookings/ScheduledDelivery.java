package com.example.bilabonnmenteksamensprojekt.models.bookings;

import com.example.bilabonnmenteksamensprojekt.models.locations.Address;

import java.sql.Date;
import java.time.LocalDate;

public class ScheduledDelivery {

    private Booking booking;
    private Address address;
    private LocalDate plannedDeliveryDate;

    public ScheduledDelivery(Booking booking, Address address, LocalDate plannedDeliveryDate) {
        this.booking = booking;
        this.address = address;
        this.plannedDeliveryDate = plannedDeliveryDate;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getPlannedDeliveryDate() {
        return plannedDeliveryDate;
    }

    public void setPlannedDeliveryDate(LocalDate plannedDeliveryDate) {
        this.plannedDeliveryDate = plannedDeliveryDate;
    }
}
