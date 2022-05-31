package com.example.bilabonnmenteksamensprojekt.models.bookings;

import com.example.bilabonnmenteksamensprojekt.models.locations.Location;

import java.sql.Date;
import java.time.LocalDate;

public class ScheduledReturn {

    private Booking booking;
    private Location dropoffLocation;
    private LocalDate plannedReturnDate;

    public ScheduledReturn(Booking booking, Location dropoffLocation, LocalDate plannedReturnDate) {
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

    public LocalDate getPlannedReturnDate() {
        return plannedReturnDate;
    }

    public void setPlannedReturnDate(LocalDate plannedReturnDate) {
        this.plannedReturnDate = plannedReturnDate;
    }
}
