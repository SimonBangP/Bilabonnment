package com.example.bilabonnmenteksamensprojekt.services.cars;

import com.example.bilabonnmenteksamensprojekt.models.cars.CarEngine;
import com.example.bilabonnmenteksamensprojekt.repositories.cars.CarEngineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarEngineService {

    @Autowired
    CarEngineRepository repository;

    public CarEngine getCarEngineById(int id) {
        return repository.getCarEngineById(id);
    }

    public int insertCarEngine(CarEngine engine) {
        if (repository.carEngineExists(engine)) {
            return repository.getEngineId(engine);
        }
        else {
            return repository.insertNewEngine(engine);
        }
    }

    public int getEngineId(CarEngine engine) {
        return repository.getEngineId(engine);
    }
}
