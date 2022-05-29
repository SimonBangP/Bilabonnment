package com.example.bilabonnmenteksamensprojekt.controllers.api;

import com.example.bilabonnmenteksamensprojekt.models.locations.Address;
import com.example.bilabonnmenteksamensprojekt.models.locations.Location;
import com.example.bilabonnmenteksamensprojekt.services.LocationsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/locations")
public class LocationsApi {

    @Autowired
    LocationsService locationsService;

    @Operation(summary = "Gets all addresses", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404", content = {})})
    @GetMapping("/addresses")
    public ResponseEntity<List<Address>> getAddresses() {
        List<Address> addresses = locationsService.getAddresses();
        if (addresses == null || addresses.size() <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(addresses, HttpStatus.OK);
        }
    }

    @Operation(summary = "Gets all locations", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404", content = {})})
    @GetMapping("/")
    public ResponseEntity<List<Location>> getLocations() {
        List<Location> locations = locationsService.getLocations();
        if (locations == null || locations.size() <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(locations, HttpStatus.OK);
        }
    }

    @Operation(summary = "Gets all pickup locations", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404", content = {})})
    @GetMapping("/pickup")
    public ResponseEntity<List<Location>> getPickupLocations() {
        List<Location> locations = locationsService.getPickupLocations();
        if (locations == null || locations.size() <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(locations, HttpStatus.OK);
        }
    }

    @Operation(summary = "Gets all dropoff locations", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404", content = {})})
    @GetMapping("/dropoff")
    public ResponseEntity<List<Location>> getDropoffLocations() {
        List<Location> locations = locationsService.getDropoffLocations();
        if (locations == null || locations.size() <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(locations, HttpStatus.OK);
        }
    }

    @Operation(summary = "Gets a specific address", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404", content = {})})
    @GetMapping("/addresses/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable int id) {
        Address address = locationsService.getAddressById(id);
        if (address == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(address, HttpStatus.OK);
        }
    }

    @Operation(summary = "Gets a specific location", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404", content = {})})
    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable int id) {
        Location location = locationsService.getLocationById(id);
        if (location == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(location, HttpStatus.OK);
        }
    }

    @Operation(summary = "Gets a specific pickup location", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404", content = {})})
    @GetMapping("/pickup/{id}")
    public ResponseEntity<Location> getPickupLocationLocationById(@PathVariable int id) {
        Location location = locationsService.getPickupLocationById(id);
        if (location == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(location, HttpStatus.OK);
        }
    }

    @Operation(summary = "Gets a specific dropoff location", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404", content = {})})
    @GetMapping("/dropoff/{id}")
    public ResponseEntity<Location> getDropoffLocationById(@PathVariable int id) {
        Location location = locationsService.getDropoffLocationById(id);
        if (location == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(location, HttpStatus.OK);
        }
    }

    @Operation(summary = "Inserts an address", responses = {@ApiResponse(responseCode = "201")})
    @PostMapping("/addresses")
    public ResponseEntity<Void> insertAddress(@RequestParam(name = "Street")String street, @RequestParam(name = "HouseNumber")int houseNumber,
                                              @RequestParam(name = "ZipCode")int zipCode, @RequestParam(name = "City")String city) {

        Address address = new Address(street, houseNumber, zipCode, city);
        locationsService.insertAddress(address);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Updates an address", responses = {@ApiResponse(responseCode = "200")})
    @PostMapping("/addresses/{id}")
    public ResponseEntity<Void> updateAddress(@PathVariable int id, @RequestParam(name = "Street")String street,
                                              @RequestParam(name = "HouseNumber")int houseNumber,
                                              @RequestParam(name = "ZipCode")int zipCode, @RequestParam(name = "City")String city) {

        Address address = new Address(street, houseNumber, zipCode, city);
        locationsService.updateAddress(id, address);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Inserts a location", responses = {@ApiResponse(responseCode = "201"), @ApiResponse(responseCode = "400")})
    @PostMapping("/")
    public ResponseEntity<Void> insertLocation(@RequestParam(name = "LocationName")String locationName, @RequestParam(name = "AddressId")int addressId) {

        Address address = locationsService.getAddressById(addressId);

        if (address == null) {
            return new ResponseEntity("An address with AddressId: " + addressId + " was not found", HttpStatus.BAD_REQUEST);
        }

        Location location = new Location(locationName, address);
        locationsService.insertLocation(location);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Updates a location", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "400")})
    @PostMapping("/{id}")
    public ResponseEntity<Void> updateLocation(@PathVariable int id, @RequestParam(name = "LocationName")String locationName,
                                               @RequestParam(name = "AddressId")int addressId) {

        Address address = locationsService.getAddressById(addressId);

        if (address == null) {
            return new ResponseEntity("An address with AddressId: " + addressId + " was not found", HttpStatus.BAD_REQUEST);
        }

        Location location = new Location(locationName, address);
        locationsService.updateLocation(id, location);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Inserts a pickup location", responses = {@ApiResponse(responseCode = "201"), @ApiResponse(responseCode = "400")})
    @PostMapping("/pickup")
    public ResponseEntity<Void> insertPickupLocation(@RequestParam(name = "LocationId")int locationId) {

        Location location = locationsService.getLocationById(locationId);

        if (location == null) {
            return new ResponseEntity("A location with LocationId: " + locationId + " was not found", HttpStatus.BAD_REQUEST);
        }

        locationsService.insertPickupLocation(location);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Updates a pickup location", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "400")})
    @PostMapping("/pickup/{id}")
    public ResponseEntity<Void> updatePickupLocation(@PathVariable int id, @RequestParam(name = "LocationId")int locationId) {

        Location location = locationsService.getLocationById(locationId);

        if (location == null) {
            return new ResponseEntity("A location with LocationId: " + locationId + " was not found", HttpStatus.BAD_REQUEST);
        }

        locationsService.updatePickupLocation(id, location);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Inserts a dropoff location", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "400")})
    @PostMapping("/dropoff")
    public ResponseEntity<Void> insertDropoffLocation(@RequestParam(name = "LocationId")int locationId) {

        Location location = locationsService.getLocationById(locationId);

        if (location == null) {
            return new ResponseEntity("A location with LocationId: " + locationId + " was not found", HttpStatus.BAD_REQUEST);
        }

        locationsService.insertDropoffLocation(location);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Updates a dropoff location", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "400")})
    @PostMapping("/dropoff/{id}")
    public ResponseEntity<Void> updateDropoffLocation(@RequestParam(name = "Id")int id, @RequestParam(name = "LocationId")int locationId) {

        Location location = locationsService.getLocationById(locationId);

        if (location == null) {
            return new ResponseEntity("A location with LocationId: " + locationId + " was not found", HttpStatus.BAD_REQUEST);
        }

        locationsService.updateDropoffLocation(id, location);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
    @Operation(summary = "Removes an address", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404")})
    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable int id) {

        Address address = locationsService.getAddressById(id);

        if (address == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        locationsService.removeAddress(address);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Removes a location", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable int id) {

        Location location = locationsService.getLocationById(id);

        if (location == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        locationsService.removeLocation(location);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Removes a pickup location", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404")})
    @DeleteMapping("/pickup/{id}")
    public ResponseEntity<Void> deletePickupLocation(@PathVariable int id) {

        Location location = locationsService.getPickupLocationById(id);

        if (location == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        locationsService.removePickupLocation(location);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Removes a dropoff location", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404")})
    @DeleteMapping("/dropoff/{id}")
    public ResponseEntity<Void> deleteDropoffLocation(@RequestParam(name = "Id")int id) {

        Location location = locationsService.getDropoffLocationById(id);

        if (location == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        locationsService.removeDropoffLocation(location);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    */
}
