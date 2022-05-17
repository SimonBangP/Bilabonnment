package com.example.bilabonnmenteksamensprojekt.repositories.cars;

import com.example.bilabonnmenteksamensprojekt.models.cars.Car;
import com.example.bilabonnmenteksamensprojekt.services.cars.CarSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class CarRepository {

    @Autowired
    JdbcTemplate template;

    @Autowired
    CarSpecificationService carSpecificationService;

    public List<Car> getCars(){
        String sql = "SELECT * FROM cars";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);

       return template.query(sql, (ResultSet rs, int rowNum) -> {
            Car foundCar = rowMapper.mapRow(rs, rowNum);

            foundCar.setCarSpecification(carSpecificationService.getSpecificationById(rs.getInt(2)));

            return foundCar;
        });
    }

    public Car getCarFromId(int id) {
        String sql = "SELECT * FROM cars WHERE CarId = ?";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            Car foundCar = rowMapper.mapRow(rs, rowNum);

            foundCar.setCarSpecification(carSpecificationService .getSpecificationById(rs.getInt(2)));

            return foundCar;
        }, id).get(0);
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
