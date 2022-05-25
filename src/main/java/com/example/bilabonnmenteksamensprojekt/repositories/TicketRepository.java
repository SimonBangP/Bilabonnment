package com.example.bilabonnmenteksamensprojekt.repositories;

import com.example.bilabonnmenteksamensprojekt.models.tickets.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketRepository {

    @Autowired
    JdbcTemplate template;

    public List<Ticket> getTickets (){
        String sql = "SELECT * FROM tickets";
        RowMapper<Ticket> rowMapper = new BeanPropertyRowMapper<>(Ticket.class);
        return template.query(sql, rowMapper);
    }
}
