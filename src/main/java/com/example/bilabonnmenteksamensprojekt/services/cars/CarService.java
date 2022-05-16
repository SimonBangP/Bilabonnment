package com.example.bilabonnmenteksamensprojekt.services.cars;

import com.example.bilabonnmenteksamensprojekt.models.cars.Car;
import com.example.bilabonnmenteksamensprojekt.repositories.cars.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public List<Car> getCars(){
        return carRepository.getCars();
    }

    public Car getCarById(int id) {
        return carRepository.getCarFromId(id);
    }

    public void createNewCar (Car car){
        carRepository.createNewCar(car);
    }
    public Car findSpecificCar (String registrationNumber)  {

       return carRepository.findSpecificCar(registrationNumber);
    }
    public boolean deleteCar (String registrationNumber){
return carRepository.deleteCar(registrationNumber);
    }
    public void updateCurrentCar (Car car, String registrationNumber){
        carRepository.updateCurrentCar(car, registrationNumber);
    }
}

