package com.example.bilabonnmenteksamensprojekt.repositories;

import com.example.bilabonnmenteksamensprojekt.models.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepository {

    @Autowired
    JdbcTemplate template;

    public List<Car> getCars(){
        String sql = "SELECT * FROM cars";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return template.query(sql, rowMapper);
    }
    public void createNewCar (Car car){}

    public void updateCurrentCar (Car car, String regNum ){}

    public Boolean deleteCar (String regNum){
        String sql = "DELETE FROM cars_features cars WHERE RegistrationNumber = ?";
        deleteCarrental(regNum);
        deleteCar2(regNum);
        return template.update(sql, regNum) > 0;
    }
    public Boolean deleteCarrental (String regNum){
        String sql ="DELETE FROM rental_contracts WHERE RegistrationNumber = ?";
        return template.update(sql, regNum) > 0;
    }

    public Boolean deleteCar2 (String regNum){
        String sql = "DELETE FROM cars WHERE RegistrationNumber = ?";
        return template.update(sql, regNum) > 0;
    }

    public Car findSpecificCar (String regNum){
        String sql = "SELECT * FROM cars WHERE RegistrationNumber = ?";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        Car car = template.queryForObject(sql, rowMapper, regNum);
        return car;
    }

}
