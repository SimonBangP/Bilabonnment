package com.example.bilabonnmenteksamensprojekt.repositories;

import com.example.bilabonnmenteksamensprojekt.models.locations.Address;
import com.example.bilabonnmenteksamensprojekt.models.locations.Location;
import com.example.bilabonnmenteksamensprojekt.models.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Repository
public class LocationsRepository {

    @Autowired
    JdbcTemplate template;

    public Address getAddressById(int id) {
        String sql = "SELECT * FROM addresses WHERE AddressId = ?";
        RowMapper<Address> rowMapper = new BeanPropertyRowMapper<>(Address.class);

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            Address foundAddress = rowMapper.mapRow(rs, rowNum);

            String getCitySql = "SELECT City FROM zipcodes WHERE ZipCode = ?";
            String city = template.query(getCitySql,  (ResultSet zipcodeRS, int rowNow) -> {
                return rs.getString(1);
            }, foundAddress.getZipCode()).get(0);

            foundAddress.setCity(city);
            return foundAddress;
        }, id).get(0);
    }

    public Location getLocationById(int id) {
        String sql = "SELECT * FROM locations WHERE LocationId = ?";
        RowMapper<Location> rowMapper = new BeanPropertyRowMapper<>(Location.class);

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            Location foundLocation = rowMapper.mapRow(rs, rowNum);

            foundLocation.setAddress(getAddressById(rs.getInt(3)));

            return foundLocation;
        }, id).get(0);
    }

    public Location getPickupLocationById(int id) {
        String sql = "SELECT * FROM locations WHERE LocationId IN (SELECT LocationId FROM pickup_locations WHERE LocationId = ?)";
        RowMapper<Location> rowMapper = new BeanPropertyRowMapper<>(Location.class);

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            Location foundLocation = rowMapper.mapRow(rs, rowNum);

            foundLocation.setAddress(getAddressById(rs.getInt(3)));

            return foundLocation;
        }, id).get(0);
    }

    public Location getDropoffLocationById(int id) {
        String sql = "SELECT * FROM locations WHERE LocationId IN (SELECT LocationId FROM dropoff_locations WHERE LocationId = ?)";
        RowMapper<Location> rowMapper = new BeanPropertyRowMapper<>(Location.class);

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            Location foundLocation = rowMapper.mapRow(rs, rowNum);

            foundLocation.setAddress(getAddressById(rs.getInt(3)));

            return foundLocation;
        }, id).get(0);
    }

}
