package com.example.bilabonnmenteksamensprojekt.controllers.api;

import com.example.bilabonnmenteksamensprojekt.models.system.Severity;
import com.example.bilabonnmenteksamensprojekt.models.system.Ticket;
import com.example.bilabonnmenteksamensprojekt.models.system.alarms.Alarm;
import com.example.bilabonnmenteksamensprojekt.models.system.alarms.WatchCategory;
import com.example.bilabonnmenteksamensprojekt.models.users.User;
import com.example.bilabonnmenteksamensprojekt.services.TicketService;
import com.example.bilabonnmenteksamensprojekt.services.users.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tickets")
public class TicketsApi {

    @Autowired
    TicketService ticketService;

    @Autowired
    UserService userService;

    @Operation(summary = "Gets all tickets", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404", content = @Content())})
    @GetMapping("/")
    public ResponseEntity<List<Ticket>> getAll() {
        List<Ticket> tickets = ticketService.getTickets();
        if (tickets == null || tickets.size() <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        }
    }

    @Operation(summary = "Gets a specific ticket", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404", content = @Content())})
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getById(@PathVariable int id) {
        Ticket ticket = ticketService.getTicketById(id);
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        }
    }

    @Operation(summary = "Inserts a ticket", responses = {@ApiResponse(responseCode = "201"), @ApiResponse(responseCode = "400")})
    @PostMapping("/")
    public ResponseEntity<Void> insert(@RequestParam(name = "UserId")int userId, @RequestParam(name = "Severity")Severity severity,
                                       @RequestParam(name = "TicketName")String name, @RequestParam(name = "Description")String description) {
        User user = userService.getUserById(userId);

        if (user == null) {
            return new ResponseEntity("User with userid: " + userId + " not found", HttpStatus.BAD_REQUEST);
        }

        Ticket ticket = new Ticket(user, severity, name, description);

        ticketService.insertTicket(ticket);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Operation(summary = "Updates a ticket", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "400")})
    @PostMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestParam(name = "UserId")int userId, @RequestParam(name = "Severity")Severity severity,
                                       @RequestParam(name = "TicketName")String name, @RequestParam(name = "Description")String description) {

        User user = userService.getUserById(userId);

        if (user == null) {
            return new ResponseEntity("User with userid: " + userId + " not found", HttpStatus.BAD_REQUEST);
        }

        Ticket ticket = new Ticket(user, severity, name, description);

        ticketService.updateTicket(id, ticket);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "Removes a ticket", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        Ticket ticket = ticketService.getTicketById(id);

        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ticketService.removeTicket(ticket);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
