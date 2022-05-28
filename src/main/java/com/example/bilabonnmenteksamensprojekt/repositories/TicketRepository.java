package com.example.bilabonnmenteksamensprojekt.repositories;

import com.example.bilabonnmenteksamensprojekt.models.system.Ticket;
import com.example.bilabonnmenteksamensprojekt.services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class TicketRepository {

    @Autowired
    JdbcTemplate template;

    @Autowired
    UserAuthenticationService userService;

    public List<Ticket> getTickets (){
        String sql = "SELECT * FROM tickets";
        RowMapper<Ticket> rowMapper = new BeanPropertyRowMapper<>(Ticket.class);

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            Ticket foundTicket = rowMapper.mapRow(rs, rowNum);

            foundTicket.setUser(userService.getUserById(rs.getInt(2)));

            return foundTicket;
        });
    }

    public void insertTicket(Ticket ticket){
        String sql = "INSERT INTO tickets VALUES (DEFAULT, ?, ?, ?, ?)";
        template.update(sql, ticket.getUser().getUserId(), ticket.getSeverity().name(), ticket.getTicketName(), ticket.getTicketDescription());

    }

    public int getCount() {
        String sql = "SELECT COUNT(TicketId) FROM tickets";

        return template.queryForObject(sql, Integer.class);
    }

    public int getCountWithWhereClause(String whereClause) {
        String sql = "SELECT COUNT(TicketId) FROM tickets WHERE " + whereClause;

        return template.queryForObject(sql, Integer.class);
    }

    public boolean ticketExists(Ticket ticket) {
        String sql = "SELECT COUNT(TicketId) FROM tickets WHERE UserId = ? AND Severity = ? AND TicketName = ? AND TicketDescription = ?";

        return template.queryForObject(sql, Integer.class, ticket.getUser().getUserId(), ticket.getSeverity().name(), ticket.getTicketName(), ticket.getTicketDescription()) > 0;
    }

}
