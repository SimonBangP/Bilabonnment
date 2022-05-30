package com.example.bilabonnmenteksamensprojekt.repositories;

import com.example.bilabonnmenteksamensprojekt.models.system.Ticket;
import com.example.bilabonnmenteksamensprojekt.services.TicketService;
import com.example.bilabonnmenteksamensprojekt.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TicketRepository {

    @Autowired
    JdbcTemplate template;

    @Autowired
    UserService userService;

    private Ticket mapRow(RowMapper<Ticket> rowMapper, ResultSet rs, int rowNum) throws SQLException {
        Ticket foundTicket = rowMapper.mapRow(rs, rowNum);

        foundTicket.setUser(userService.getUserById(rs.getInt(2)));

        return foundTicket;
    }

    public List<Ticket> getTickets (){
        String sql = "SELECT * FROM tickets";
        RowMapper<Ticket> rowMapper = new BeanPropertyRowMapper<>(Ticket.class);

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            return mapRow(rowMapper, rs, rowNum);
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

    public Ticket getTicketById(int id) {
        String sql = "SELECT * FROM tickets WHERE TicketId = ?";
        RowMapper<Ticket> rowMapper = new BeanPropertyRowMapper<>(Ticket.class);

        List<Ticket> tickets = template.query(sql, (ResultSet rs, int rowNum) -> {
            return mapRow(rowMapper, rs, rowNum);
        }, id);

        if (tickets.size() <= 0) {
            return null;
        }
        else {
            return tickets.get(0);
        }
    }

    public void updateTicket(int id, Ticket ticket) {
        String sql = "UPDATE tickets SET UserId = ?, Severity = ?, TicketName = ?, TicketDescription = ? WHERE TicketId = ?";

        template.update(sql, ticket.getUser().getUserId(), ticket.getSeverity().name(), ticket.getTicketName(), ticket.getTicketDescription(), id);
    }

    public void removeTicket(Ticket ticket) {
        String sql = "DELETE FROM tickets WHERE TicketId = ?";

        template.update(sql, getTicketId(ticket));
    }

    public int getTicketId(Ticket ticket){
        String sql = "SELECT TicketId FROM tickets WHERE Severity = ? AND TicketName = ? AND TicketDescription = ?";

        return template.queryForObject(sql, Integer.class, ticket.getSeverity().name(), ticket.getTicketName(), ticket.getTicketDescription());
    }


}
