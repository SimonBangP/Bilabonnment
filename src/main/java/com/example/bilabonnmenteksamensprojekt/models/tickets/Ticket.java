package com.example.bilabonnmenteksamensprojekt.models.tickets;

import com.example.bilabonnmenteksamensprojekt.models.users.User;

public class Ticket {
    private int ticketId;
    private User user;
    private String severity;
    private String ticketName;
    private String ticketDescription;


    public Ticket(){}

    public Ticket(int ticketId, User userId, String severity, String ticketName, String ticketDescription) {
        this.ticketId = ticketId;
        this.user = userId;
        this.severity = severity;
        this.ticketName = ticketName;
        this.ticketDescription = ticketDescription;
    }

    public Ticket(User user, String severity, String ticketName, String ticketDescription) {
        this.user = user;
        this.severity = severity;
        this.ticketName = ticketName;
        this.ticketDescription = ticketDescription;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getTicketDescription() {
        return ticketDescription;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }


    public String getAllInfoToString(){
        return ("Sag: " + ticketId + ", behandles af: " + user.getFirstName() + " " + user.getLastName() + " - " + ticketName + " - " + severity.toString() + " PRIORITY");
    }

}
