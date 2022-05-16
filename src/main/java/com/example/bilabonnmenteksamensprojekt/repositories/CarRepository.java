package com.example.bilabonnmenteksamensprojekt.repositories;

import com.example.bilabonnmenteksamensprojekt.models.cars.Car;
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
}
