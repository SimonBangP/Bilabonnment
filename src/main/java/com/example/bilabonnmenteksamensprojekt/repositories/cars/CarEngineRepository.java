package com.example.bilabonnmenteksamensprojekt.repositories.cars;

import com.example.bilabonnmenteksamensprojekt.models.cars.CarEngine;
import com.example.bilabonnmenteksamensprojekt.models.cars.CarSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class CarEngineRepository {

    @Autowired
    JdbcTemplate template;

    public CarEngine getCarEngineById(int id) {
        String sql = "SELECT * FROM engine_configurations WHERE EngineId = ?";
        RowMapper<CarEngine> rowMapper = new BeanPropertyRowMapper<>(CarEngine.class);

        return template.query(sql, rowMapper, id).get(0);
    }

}
