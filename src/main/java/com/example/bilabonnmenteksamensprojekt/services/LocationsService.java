package com.example.bilabonnmenteksamensprojekt.services;

import com.example.bilabonnmenteksamensprojekt.models.locations.Address;
import com.example.bilabonnmenteksamensprojekt.models.locations.Location;
import com.example.bilabonnmenteksamensprojekt.repositories.LocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationsService {

    @Autowired
    LocationsRepository repository;

    public Address getAddressById(int id) {
        return repository.getAddressById(id);
    }

    public Location getLocationById(int id) {
        return repository.getLocationById(id);
    }

    public Location getPickupLocationById(int id) {
        return repository.getPickupLocationById(id);
    }

    public Location getDropoffLocationById(int id) {
        return repository.getDropoffLocationById(id);
    }

    public void insertAddress(Address address) {
        repository.insertNewAddress(address);
    }

    public void checkAddress (Address address){
        repository.checkAddress(address);
    }

    public void updateAddress(int id, Address address) {
        repository.updateAddress(id, address);
    }

    public void insertLocation(Location location) {
        repository.insertLocation(location);
    }

    public void updateLocation(int id, Location location) {
        repository.updateLocation(id, location);
    }

    public void insertPickupLocation(Location location) {
        repository.insertPickupLocation(location);
    }

    public void updatePickupLocation(int id, Location location) {
        repository.updatePickupLocation(id, location);
    }

    public void insertDropoffLocation(Location location) {
        repository.insertDropoffLocation(location);
    }

    public void updateDropoffLocation(int id, Location location) {
        repository.updateDropoffLocation(id, location);
    }

    public List<Address> getAddresses() {
        return repository.getAddresses();
    }

    public List<Location> getLocations() {
        return repository.getLocations();
    }

    public List<Location> getPickupLocations() {
        return repository.getPickupLocations();
    }

    public List<Location> getDropoffLocations() {
        return repository.getDropoffLocations();
    }

    /*
    public void removeAddress(Address address) {
        repository.removeAddress()
    }

    public void removeLocation(Location location) {
    }

    public void removePickupLocation(Location location) {
    }

    public void removeDropoffLocation(Location location) {
    }
    */
}
