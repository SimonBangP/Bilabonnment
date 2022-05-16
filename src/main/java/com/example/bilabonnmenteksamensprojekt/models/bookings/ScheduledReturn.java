package com.example.bilabonnmenteksamensprojekt.models.bookings;

import com.example.bilabonnmenteksamensprojekt.models.locations.Location;

import java.sql.Date;

public class ScheduledReturn {

    private Booking booking;
    private Location dropoffLocation;
    private Date plannedReturnDate;

    public ScheduledReturn(Booking booking, Location dropoffLocation, Date plannedReturnDate) {
        this.booking = booking;
        this.dropoffLocation = dropoffLocation;
        this.plannedReturnDate = plannedReturnDate;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Location getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(Location dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    public Date getPlannedReturnDate() {
        return plannedReturnDate;
    }

    public void setPlannedReturnDate(Date plannedReturnDate) {
        this.plannedReturnDate = plannedReturnDate;
    }
}
