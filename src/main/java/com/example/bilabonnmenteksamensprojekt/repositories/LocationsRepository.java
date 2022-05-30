package com.example.bilabonnmenteksamensprojekt.repositories;

import com.example.bilabonnmenteksamensprojekt.models.locations.Address;
import com.example.bilabonnmenteksamensprojekt.models.locations.Location;
import com.example.bilabonnmenteksamensprojekt.models.system.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LocationsRepository {

    @Autowired
    JdbcTemplate template;

    private Address mapAddressRow(RowMapper<Address> rowMapper, ResultSet rs, int rowNum) throws SQLException {
        Address foundAddress = rowMapper.mapRow(rs, rowNum);

        String getCitySql = "SELECT City FROM zipcodes WHERE ZipCode = ?";
        String city = template.query(getCitySql,  (ResultSet zipcodeRS, int rowNow) -> {
            return zipcodeRS.getString(1);
        }, foundAddress.getZipCode()).get(0);

        foundAddress.setCity(city);
        return foundAddress;
    }

    private Location mapLocationRow(RowMapper<Location> rowMapper, ResultSet rs, int rowNum) throws SQLException {
        Location foundLocation = rowMapper.mapRow(rs, rowNum);

        foundLocation.setAddress(getAddressById(rs.getInt(3)));

        return foundLocation;
    }


    public Location getLocationById(int id) {
        String sql = "SELECT * FROM locations WHERE LocationId = ?";
        RowMapper<Location> rowMapper = new BeanPropertyRowMapper<>(Location.class);

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            return mapLocationRow(rowMapper, rs, rowNum);
        }, id).get(0);
    }

    public Location getPickupLocationById(int id) {
        String sql = "SELECT * FROM locations WHERE LocationId IN (SELECT LocationId FROM pickup_locations WHERE LocationId = ?)";
        RowMapper<Location> rowMapper = new BeanPropertyRowMapper<>(Location.class);

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            return mapLocationRow(rowMapper, rs, rowNum);
        }, id).get(0);
    }

    public Location getDropoffLocationById(int id) {
        String sql = "SELECT * FROM locations WHERE LocationId IN (SELECT LocationId FROM dropoff_locations WHERE LocationId = ?)";
        RowMapper<Location> rowMapper = new BeanPropertyRowMapper<>(Location.class);

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            return mapLocationRow(rowMapper, rs, rowNum);
        }, id).get(0);
    }

    public void insertNewAddress(Address address) {
        String sql = "INSERT INTO addresses VALUES (DEFAULT, ?, ?, ?)";

        insertZipCode(address.getZipCode(), address.getCity());

        template.update(sql, address.getStreet(), address.getHouseNumber(), address.getZipCode());
        address.setAddressId(getAddressId(address));
    }

    private void insertZipCode(int zipCode, String city) {
        if (zipCodeExists(zipCode)) {
            return;
        }

        String sql = "INSERT INTO zipcodes VALUES (?, ?)";
        template.update(sql, zipCode, city);
    }

    private boolean zipCodeExists(int zipCode) {
        String sql = "SELECT COUNT(ZipCode) FROM zipcodes WHERE ZipCode = ?";

        return template.queryForObject(sql, Integer.class, zipCode) > 0;
    }

    public void updateAddress(int id, Address address) {
        String sql = "UPDATE addresses SET Street = ?, HouseNumber = ?, ZipCode = ? WHERE AddressId = ?";

        insertZipCode(address.getZipCode(), address.getCity());

        template.update(sql, address.getStreet(), address.getHouseNumber(), address.getZipCode(), id);
    }

    public void insertLocation(Location location) {
        String sql = "INSERT INTO locations VALUES (DEFAULT, ?, ?)";

        template.update(sql, location.getLocationName(), location.getAddress().getAddressId());
    }

    public void updateLocation(int id, Location location) {
        String sql = "UPDATE locations SET LocationName = ?, AddressId = ? WHERE LocationId = ?";

        template.update(sql, location.getLocationName(), location.getAddress().getAddressId(), id);
    }

    public void insertPickupLocation(Location location) {
        String sql = "INSERT INTO pickup_locations VALUES (DEFAULT, ?)";

        template.update(sql, location.getLocationId());
    }

    public void updatePickupLocation(int id, Location location) {
        String sql = "UPDATE pickup_locations SET LocationId = ? WHERE PickupLocationId = ?";

        template.update(sql, location.getLocationId(), id);
    }

    public void insertDropoffLocation(Location location) {
        String sql = "INSERT INTO dropoff_locations VALUES (DEFAULT, ?)";

        template.update(sql, location.getLocationId());
    }

    public void updateDropoffLocation(int id, Location location) {
        String sql = "UPDATE dropoff_locations SET LocationId = ? WHERE DropoffLocationId = ?";

        template.update(sql, location.getLocationId(), id);
    }

    public List<Address> getAddresses() {
        String sql = "SELECT * FROM addresses";
        RowMapper<Address> rowMapper = new BeanPropertyRowMapper<>(Address.class);

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            return mapAddressRow(rowMapper, rs, rowNum);
        });
    }

    public List<Location> getLocations() {
        String sql = "SELECT * FROM locations";
        RowMapper<Location> rowMapper = new BeanPropertyRowMapper<>(Location.class);

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            return mapLocationRow(rowMapper, rs, rowNum);
        });
    }

    public List<Location> getPickupLocations() {
        String sql = "SELECT * FROM locations WHERE LocationId IN (SELECT LocationId FROM pickup_locations)";
        RowMapper<Location> rowMapper = new BeanPropertyRowMapper<>(Location.class);

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            return mapLocationRow(rowMapper, rs, rowNum);
        });
    }

    public List<Location> getDropoffLocations() {
        String sql = "SELECT * FROM locations WHERE LocationId IN (SELECT LocationId FROM dropoff_locations)";
        RowMapper<Location> rowMapper = new BeanPropertyRowMapper<>(Location.class);

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            return mapLocationRow(rowMapper, rs, rowNum);
        });
    }

    public boolean addressExists(Address address) {
        String sql = "SELECT COUNT(AddressId) FROM addresses WHERE Street = ? AND HouseNumber = ? AND ZipCode = ?";

        return template.queryForObject(sql, Integer.class, address.getStreet(), address.getHouseNumber(), address.getZipCode()) > 0;
    }

    public int getAddressId(Address address) {
        String sql = "SELECT AddressId FROM addresses WHERE Street = ? AND HouseNumber = ? AND ZipCode = ?";

        return template.queryForObject(sql, Integer.class, address.getStreet(), address.getHouseNumber(), address.getZipCode());
    }

    public Address getAddressById(int id) {
        String sql = "SELECT * FROM addresses WHERE AddressId = ?";
        RowMapper<Address> rowMapper = new BeanPropertyRowMapper<>(Address.class);

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            return mapAddressRow(rowMapper, rs, rowNum);
        }, id).get(0);
    }

    public void checkAddress(Address address) {
        if (!addressExists(address)) {
            insertNewAddress(address);
        }
        else {
            address.setAddressId(getAddressId(address));
        }
    }
}
