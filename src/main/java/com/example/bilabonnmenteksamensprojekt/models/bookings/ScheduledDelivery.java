package com.example.bilabonnmenteksamensprojekt.models.bookings;

import com.example.bilabonnmenteksamensprojekt.models.locations.Address;

import java.sql.Date;

public class ScheduledDelivery {

    private Booking booking;
    private Address address;
    private Date plannedDeliveryDate;

    public ScheduledDelivery(Booking booking, Address address, Date plannedDeliveryDate) {
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

    public Date getPlannedDeliveryDate() {
        return plannedDeliveryDate;
    }

    public void setPlannedDeliveryDate(Date plannedDeliveryDate) {
        this.plannedDeliveryDate = plannedDeliveryDate;
    }
}
