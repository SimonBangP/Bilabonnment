package com.example.bilabonnmenteksamensprojekt.models.bookings;

import com.example.bilabonnmenteksamensprojekt.models.customers.Customer;
import com.example.bilabonnmenteksamensprojekt.models.locations.Location;
import com.example.bilabonnmenteksamensprojekt.models.cars.Car;

import java.sql.Date;

public class Booking {

    private int bookingId;
    private Customer customer;
    private Car car;
    private Location pickupLocation;
    private Date deliveryDate;
    private Date returnDate;
    private boolean completed;

    public Booking() {

    }

    public Booking(int bookingId, Customer customer, Car car, Location pickupLocation, Date deliveryDate, Date returnDate, boolean completed) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.car = car;
        this.pickupLocation = pickupLocation;
        this.deliveryDate = deliveryDate;
        this.returnDate = returnDate;
        this.completed = completed;
    }

    public Booking(Customer customer, Car car, Location pickupLocation, Date deliveryDate, Date returnDate, boolean completed) {
        this.customer = customer;
        this.car = car;
        this.pickupLocation = pickupLocation;
        this.deliveryDate = deliveryDate;
        this.returnDate = returnDate;
        this.completed = completed;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Location getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(Location pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getAllInfoToString(){
        if(deliveryDate!=null) {
            if(returnDate!=null) {
                return (customer.getFirstName() + " " + customer.getLastName() + ", " + car.getCarSpecification().getBrand() + " " + car.getCarSpecification().getModel() + ", afhentes: " + deliveryDate.toString() + ", leveres: " + returnDate.toString());
            }
            return null;
        }
        return null;
    }

}
