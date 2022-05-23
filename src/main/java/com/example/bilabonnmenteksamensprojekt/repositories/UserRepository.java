package com.example.bilabonnmenteksamensprojekt.repositories;

import com.example.bilabonnmenteksamensprojekt.models.cars.Car;
import com.example.bilabonnmenteksamensprojekt.models.cars.CarEngine;
import com.example.bilabonnmenteksamensprojekt.models.users.User;
import com.example.bilabonnmenteksamensprojekt.services.LocationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate template;

    @Autowired
    LocationsService locationsService;

    public User getByUsername(String username) {
        String sql = "SELECT * FROM users WHERE Username = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);

        List<User> users = template.query(sql, (ResultSet rs, int rowNum) -> {
            User foundUser = rowMapper.mapRow(rs, rowNum);

            foundUser.setLocation(locationsService.getLocationById(rs.getInt(4)));

            return foundUser;
        }, username);

        if (users.size() <= 0) {
            return null;
        }
        else {
            return users.get(0);
        }
    }

}
