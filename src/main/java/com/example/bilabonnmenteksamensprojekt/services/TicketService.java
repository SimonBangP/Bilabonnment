package com.example.bilabonnmenteksamensprojekt.services;

import com.example.bilabonnmenteksamensprojekt.models.system.Ticket;
import com.example.bilabonnmenteksamensprojekt.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService implements SearchableAlarmService{

    @Autowired
    TicketRepository repository;

    public List<Ticket> getTickets (){
        return repository.getTickets();
    }

    public void insertTicket(Ticket ticket){ repository.insertTicket(ticket);}

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
}


