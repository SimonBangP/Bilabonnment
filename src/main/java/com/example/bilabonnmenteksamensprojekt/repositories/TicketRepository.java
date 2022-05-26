package com.example.bilabonnmenteksamensprojekt.repositories;

import com.example.bilabonnmenteksamensprojekt.models.tickets.Tickets;
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

    public List<Tickets> getTickets (){
        String sql = "SELECT * FROM tickets";
        RowMapper<Tickets> rowMapper = new BeanPropertyRowMapper<>(Tickets.class);
        return template.query(sql, rowMapper);
    }
}
