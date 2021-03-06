package com.example.bilabonnmenteksamensprojekt.services.cars;

import com.example.bilabonnmenteksamensprojekt.models.cars.CarSpecification;
import com.example.bilabonnmenteksamensprojekt.repositories.cars.CarSpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarSpecificationService {

    @Autowired
    CarSpecificationRepository repository;

    public CarSpecification getSpecificationById(int id) {
        return repository.getSpecificationById(id);
    }

    public int insertCarSpecification(CarSpecification carSpecification) {
        if (repository.carSpecificationExists(carSpecification)) {
            return repository.getCarSpecificationId(carSpecification);
        }
        else {
            return repository.insertNewCarSpecification(carSpecification);
        }
    }

    public int getCarSpecificationId(CarSpecification carSpecification){
        return repository.getCarSpecificationId(carSpecification);
    }
}
