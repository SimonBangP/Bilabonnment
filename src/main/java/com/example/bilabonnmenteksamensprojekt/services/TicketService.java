package com.example.bilabonnmenteksamensprojekt.services;

import com.example.bilabonnmenteksamensprojekt.models.system.Ticket;
import com.example.bilabonnmenteksamensprojekt.repositories.TicketRepository;
import com.example.bilabonnmenteksamensprojekt.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService implements SearchableAlarmService{

    @Autowired
    TicketRepository repository;

    @Autowired
    UserService userService;

    public List<Ticket> getTickets (){
        return repository.getTickets();
    }

    public void insertTicket(Ticket ticket) {
        userService.insertUser(ticket.getUser());
        repository.insertTicket(ticket);
    }

    public boolean ticketExists(Ticket ticket) {
        return repository.ticketExists(ticket);
    }

    @Override
    public int getCount() {
        return repository.getCount();
    }

    @Override
    public int getCountWithWhereClause(String whereClause) {
        return repository.getCountWithWhereClause(whereClause);
    }

    public Ticket getTicketById(int id) {
        return repository.getTicketById(id);
    }

    public void updateTicket(int id, Ticket ticket) {
        repository.updateTicket(id, ticket);
    }

    public void removeTicket(Ticket ticket) {
        repository.removeTicket(ticket);
    }

    public int getTicketId(Ticket ticket){
        return repository.getTicketId(ticket);
    }
}


