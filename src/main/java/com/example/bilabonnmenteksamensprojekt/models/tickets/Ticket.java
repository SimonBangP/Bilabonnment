package com.example.bilabonnmenteksamensprojekt.models.tickets;

public class Ticket {
    private int ticketId;
    private int userId;
    private String serverity;
    private String ticketName;
    private String ticketDescription;


    public Ticket (){}

    public Ticket(int ticketId, int userId, String serverity, String ticketName, String ticketDescription) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.serverity = serverity;
        this.ticketName = ticketName;
        this.ticketDescription = ticketDescription;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getServerity() {
        return serverity;
    }

    public void setServerity(String serverity) {
        this.serverity = serverity;
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
}
