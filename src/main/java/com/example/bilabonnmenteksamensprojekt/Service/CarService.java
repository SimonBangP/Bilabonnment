package com.example.bilabonnmenteksamensprojekt.Service;

import com.example.bilabonnmenteksamensprojekt.Model.Car;
import com.example.bilabonnmenteksamensprojekt.Repository.CarRepository;
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
}
