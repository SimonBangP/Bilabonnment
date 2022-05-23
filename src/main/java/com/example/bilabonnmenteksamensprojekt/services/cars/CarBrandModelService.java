package com.example.bilabonnmenteksamensprojekt.services.cars;

import com.example.bilabonnmenteksamensprojekt.repositories.cars.CarBrandModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarBrandModelService {

    @Autowired
    CarBrandModelRepository repository;

    public int insertModel(String brand, String model) {
        if (repository.modelExists(brand, model)){
            return repository.getModelId(brand, model);
        }
        else {
            return repository.insertNewModel(brand, model);
        }
    }

    public int getModelId(String brand, String model) {
        return repository.getModelId(brand, model);
    }
}
