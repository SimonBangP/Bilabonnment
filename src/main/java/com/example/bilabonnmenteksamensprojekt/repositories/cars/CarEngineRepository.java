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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CarEngineRepository {

    @Autowired
    JdbcTemplate template;

    public CarEngine getCarEngineById(int id) {
        String sql = "SELECT * FROM engine_configurations WHERE EngineId = ?";
        RowMapper<CarEngine> rowMapper = new BeanPropertyRowMapper<>(CarEngine.class);

        return template.query(sql, rowMapper, id).get(0);
    }

    public int insertNewEngine(CarEngine engine) {
        String sql = "INSERT INTO engine_configurations VALUES (DEFAULT, ?, ?, ?, ?, ?)";

        template.update(sql, engine.getEnginePower(), engine.getGearType().name(), engine.getFuelType().name(), engine.getEmissions(), engine.getKilometersPerLiter());
        return getEngineId(engine);
    }

    public int getEngineId(CarEngine engine) {
        String sql = "SELECT EngineId FROM engine_configurations WHERE EnginePower = ? AND GearType = ? AND FuelType = ? AND Emissions = ? AND KilometersPerLiter = ?";

        List<Integer> ids = template.query(sql, (ResultSet rs, int rowNum) -> rs.getInt(1), engine.getEnginePower(),
                   engine.getGearType().name(),
                   engine.getFuelType().name(),
                   engine.getEmissions(),
                   engine.getKilometersPerLiter());

        if (ids.size() <= 0) {
            return -1;
        }
        else {
            return ids.get(0);
        }
    }

    public boolean carEngineExists(CarEngine engine) {
        String sql = "SELECT COUNT(*) FROM engine_configurations WHERE EnginePower = ? AND GearType = ? AND FuelType = ? AND Emissions = ? AND KilometersPerLiter = ?";

        RowMapper<Integer> rowMapper = new BeanPropertyRowMapper<>(Integer.class);
        return template.query(sql, (ResultSet rs, int rowNum) -> {
                    return rs.getInt(1);
                }, engine.getEnginePower(),
                   engine.getGearType().name(),
                   engine.getFuelType().name(),
                   engine.getEmissions(),
                   engine.getKilometersPerLiter()).get(0) >= 1;
    }

}
