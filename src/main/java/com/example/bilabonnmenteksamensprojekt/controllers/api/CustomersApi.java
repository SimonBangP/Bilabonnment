package com.example.bilabonnmenteksamensprojekt.controllers.api;

import com.example.bilabonnmenteksamensprojekt.models.cars.Car;
import com.example.bilabonnmenteksamensprojekt.models.customers.Customer;
import com.example.bilabonnmenteksamensprojekt.models.locations.Address;
import com.example.bilabonnmenteksamensprojekt.models.locations.Location;
import com.example.bilabonnmenteksamensprojekt.models.system.Severity;
import com.example.bilabonnmenteksamensprojekt.models.system.alarms.Alarm;
import com.example.bilabonnmenteksamensprojekt.models.system.alarms.WatchCategory;
import com.example.bilabonnmenteksamensprojekt.models.users.User;
import com.example.bilabonnmenteksamensprojekt.services.CustomerService;
import com.example.bilabonnmenteksamensprojekt.services.LocationsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
public class CustomersApi {

    @Autowired
    CustomerService customerService;

    @Autowired
    LocationsService locationsService;

    @Operation(summary = "Gets all customers", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404", content = @Content())})
    @GetMapping("/")
    public ResponseEntity<List<Customer>> getAll() {
        List<Customer> customers = customerService.getCustomers();
        if (customers == null || customers.size() <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }
    }

    @Operation(summary = "Gets a specific customer", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404", content = @Content())})
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable int id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
    }

    @Operation(summary = "Inserts an customer", responses = {@ApiResponse(responseCode = "201"), @ApiResponse(responseCode = "400")})
    @PostMapping("/")
    public ResponseEntity<Void> insert(@RequestParam(name = "FirstName")String firstName, @RequestParam("LastName")String lastName,
                                       @RequestParam(name = "AddressId")int addressId, @RequestParam(name = "IdentityValidated")boolean identityValidated,
                                       @RequestParam(name = "CreditValidated")boolean creditValidated) {

        Address address = locationsService.getAddressById(addressId);

        if (address == null) {
            return new ResponseEntity("Address with addressid: " + addressId + " not found", HttpStatus.BAD_REQUEST);
        }

        Customer customer = new Customer(firstName, lastName, address, identityValidated, creditValidated, null);
        customerService.insertCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Updates an alarm", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "400")})
    @PostMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestParam(name = "FirstName")String firstName, @RequestParam("LastName")String lastName,
                                       @RequestParam(name = "AddressId")int addressId, @RequestParam(name = "IdentityValidated")boolean identityValidated,
                                       @RequestParam(name = "CreditValidated")boolean creditValidated) {

        Address address = locationsService.getAddressById(addressId);

        if (address == null) {
            return new ResponseEntity("Address with addressid: " + addressId + " not found", HttpStatus.BAD_REQUEST);
        }

        Customer customer = new Customer(firstName, lastName, address, identityValidated, creditValidated, null);
        customerService.updateCustomer(id, customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
