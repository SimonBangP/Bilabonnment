package com.example.bilabonnmenteksamensprojekt.models.cars.repairs;

import com.example.bilabonnmenteksamensprojekt.models.bookings.Booking;
import com.example.bilabonnmenteksamensprojekt.models.users.User;

public class DamageReport {

    private Booking booking;
    private User user;
    private String description;

    public DamageReport(Booking booking, User user, String description) {
        this.booking = booking;
        this.user = user;
        this.description = description;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
