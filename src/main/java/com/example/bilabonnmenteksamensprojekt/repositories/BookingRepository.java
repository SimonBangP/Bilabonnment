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
    public List<Booking> getActiveBookings() {
        String sql = "SELECT * FROM bookings WHERE Completed = 1";

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

    public void endBooking(int bookingId){
        String sql = "UPDATE bookings SET Completed = 0 WHERE bookingId = ?";
        template.update(sql, bookingId);
    }

    public int getCount() {
        String sql = "SELECT COUNT(BookingId) FROM bookings";

        return template.queryForObject(sql, Integer.class);
    }

    public int getCountWithWhereClause(String whereClause) {
        String sql = "SELECT COUNT(BookingId) FROM bookings WHERE " + whereClause;

        return template.queryForObject(sql, Integer.class);
    }

    public void insertBooking(Booking booking) {
        String sql = "INSERT INTO bookings VALUES (DEFAULT, ?, ?, ?, ?, ?, DEFAULT)";

        template.update(sql, booking.getCustomer().getCustomerId(), booking.getCar().getCarId(), booking.getPickupLocation().getLocationId(),
                booking.getDeliveryDate(), booking.getReturnDate());
    }

    public void updateBooking(int id, Booking booking) {
        String sql = "UPDATE bookings SET CustomerId = ?, CarId = ?, PickupLocationId = ?, DeliveryDate = ?, ReturnDate = ?, Completed = ?";

        template.update(sql, booking.getCustomer().getCustomerId(), booking.getCar().getCarId(), booking.getPickupLocation().getLocationId(),
                booking.getDeliveryDate(), booking.getReturnDate(), booking.isCompleted());
    }
}
