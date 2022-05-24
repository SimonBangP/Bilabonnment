package com.example.bilabonnmenteksamensprojekt.repositories;

import com.example.bilabonnmenteksamensprojekt.models.bookings.Booking;
import com.example.bilabonnmenteksamensprojekt.services.CustomerService;
import com.example.bilabonnmenteksamensprojekt.services.LocationsService;
import com.example.bilabonnmenteksamensprojekt.services.cars.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class BookingRepository {

    @Autowired
    JdbcTemplate template;

    @Autowired
    CarService carService;

    @Autowired
    LocationsService locationsService;

    @Autowired
    CustomerService customerService;

    public List<Booking> getBookings() {
        String sql = "SELECT * FROM bookings";

        RowMapper<Booking> rowMapper = new BeanPropertyRowMapper<>(Booking.class);

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            Booking foundBooking = rowMapper.mapRow(rs, rowNum);

            foundBooking.setCustomer(customerService.getCustomerById(rs.getInt(2)));
            foundBooking.setCar(carService.getCarById(rs.getInt(3)));
            foundBooking.setPickupLocation(locationsService.getPickupLocationById(rs.getInt(4)));

            return foundBooking;
        });
    }

    public Booking getBookingById(int id) {
        String sql = "SELECT * FROM bookings WHERE BookingId = ?";

        RowMapper<Booking> rowMapper = new BeanPropertyRowMapper<>(Booking.class);

        List<Booking> bookings = template.query(sql, (ResultSet rs, int rowNum) -> {
            Booking foundBooking = rowMapper.mapRow(rs, rowNum);

            foundBooking.setCustomer(customerService.getCustomerById(rs.getInt(2)));
            foundBooking.setCar(carService.getCarById(rs.getInt(3)));
            foundBooking.setPickupLocation(locationsService.getPickupLocationById(rs.getInt(4)));

            return foundBooking;
        }, id);

        if (bookings.size() <= 0) {
            return null;
        }
        else {
            return bookings.get(0);
        }
    }
}
