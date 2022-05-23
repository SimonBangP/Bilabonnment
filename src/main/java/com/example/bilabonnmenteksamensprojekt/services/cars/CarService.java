package com.example.bilabonnmenteksamensprojekt.services.cars;

import com.example.bilabonnmenteksamensprojekt.models.cars.Car;
import com.example.bilabonnmenteksamensprojekt.repositories.cars.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository repository;

    public List<Car> getCars(){
        return repository.getCars();
    }

    public Car getCarById(int id) {
        return repository.getCarFromId(id);
    }

    public int insertCar (Car car){
        if (repository.carExists(car)) {
            return repository.getCarId(car);
        }
        else {
            return repository.insertNewCar(car);
        }
    }

    public void removeCar(Car car) {
        int carId = repository.getCarId(car);
        repository.removeCarById(carId);
    }

    public void removeCarById(int id) {
        repository.removeCarById(id);
    }
}

