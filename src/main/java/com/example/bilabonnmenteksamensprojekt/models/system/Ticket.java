package com.example.bilabonnmenteksamensprojekt.models.system;

import com.example.bilabonnmenteksamensprojekt.models.users.User;

public class Ticket {

    private int TicketId;
    private User user;
    private Severity severity;
    private String TicketName;
    private String description;

    public Ticket(int ticketId, User user, Severity severity, String ticketName, String description) {
        TicketId = ticketId;
        this.user = user;
        this.severity = severity;
        TicketName = ticketName;
        this.description = description;
    }

    public Ticket(User user, Severity severity, String ticketName, String description) {
        this.user = user;
        this.severity = severity;
        TicketName = ticketName;
        this.description = description;
    }

    public int getTicketId() {
        return TicketId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public String getTicketName() {
        return TicketName;
    }

    public void setTicketName(String ticketName) {
        TicketName = ticketName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
