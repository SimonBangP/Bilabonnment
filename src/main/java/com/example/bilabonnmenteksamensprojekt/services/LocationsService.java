package com.example.bilabonnmenteksamensprojekt.services;

import com.example.bilabonnmenteksamensprojekt.models.locations.Address;
import com.example.bilabonnmenteksamensprojekt.models.locations.Location;
import com.example.bilabonnmenteksamensprojekt.repositories.LocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationsService {

    @Autowired
    LocationsRepository locationsRepository;

    public Address getAddressById(int id) {
        return locationsRepository.getAddressById(id);
    }

    public Location getLocationById(int id) {
        return locationsRepository.getLocationById(id);
    }
}
