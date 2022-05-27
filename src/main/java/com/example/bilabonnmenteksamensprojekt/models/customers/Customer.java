package com.example.bilabonnmenteksamensprojekt.models.customers;

import com.example.bilabonnmenteksamensprojekt.models.locations.Address;

import java.sql.Timestamp;

public class Customer {

    private int customerId;
    private String firstName;
    private String lastName;
    private Address address;
    private boolean identityValidated;
    private boolean creditValidated;
    private Timestamp createdAt;

    public Customer() {

    }

    public Customer(int customerId, String firstName, String lastName, Address address, boolean identityValidated, boolean creditValidated, Timestamp createdAt) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.identityValidated = identityValidated;
        this.creditValidated = creditValidated;
        this.createdAt = createdAt;
    }

    public Customer(String firstName, String lastName, Address address, boolean identityValidated, boolean creditValidated, Timestamp createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.identityValidated = identityValidated;
        this.creditValidated = creditValidated;
        this.createdAt = createdAt;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isIdentityValidated() {
        return identityValidated;
    }

    public void setIdentityValidated(boolean identityValidated) {
        this.identityValidated = identityValidated;
    }

    public boolean isCreditValidated() {
        return creditValidated;
    }

    public void setCreditValidated(boolean creditValidated) {
        this.creditValidated = creditValidated;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getAllInfoToString(){
        return(firstName + " " + lastName + ", " + address.getZipCode() + " " + address.getStreet() + " " + address.getHouseNumber());
    }
}
