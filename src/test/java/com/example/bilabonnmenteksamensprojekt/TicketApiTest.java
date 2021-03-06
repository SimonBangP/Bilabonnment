package com.example.bilabonnmenteksamensprojekt;

import com.example.bilabonnmenteksamensprojekt.models.system.Severity;
import com.example.bilabonnmenteksamensprojekt.models.system.Ticket;
import com.example.bilabonnmenteksamensprojekt.services.TicketService;
import com.example.bilabonnmenteksamensprojekt.services.users.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TicketApiTest {

    @Autowired
    TicketService ticketService;

    @Autowired
    UserService userService;


    @Test
    public void testTicket(){
        Ticket ticket = new Ticket(userService.getUserByUsername("gruppe1"), Severity.Medium, "Test", "Test");

        insertTicket(ticket);

        ticket.setTicketId(ticketService.getTicketId(ticket));

        updateTicket(ticket);

        deleteTicket(ticket);
    }
    private void insertTicket(Ticket ticket){
        ticketService.insertTicket(ticket);
        if (!ticketService.ticketExists(ticket)){
            throw new IllegalArgumentException("Ticket findes ikke i databasen");
        }
    }
    private void updateTicket(Ticket ticket){

        ticket.setTicketDescription("ændret");

        ticketService.updateTicket(ticket.getTicketId(), ticket);

        if (!ticketService.ticketExists(ticket)){
            throw new IllegalArgumentException("Ticket er ikke opdateret i databasen");
        }
    }

    private void deleteTicket(Ticket ticket){

        ticketService.removeTicket(ticket);

        if (ticketService.ticketExists(ticket)){
            throw new IllegalArgumentException("Ticket er ikke slettet fra databasen");
        }
    }
}
