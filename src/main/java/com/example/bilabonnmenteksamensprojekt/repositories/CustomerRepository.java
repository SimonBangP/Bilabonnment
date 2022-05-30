package com.example.bilabonnmenteksamensprojekt.repositories;

import com.example.bilabonnmenteksamensprojekt.models.bookings.Booking;
import com.example.bilabonnmenteksamensprojekt.models.customers.Customer;
import com.example.bilabonnmenteksamensprojekt.services.LocationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class CustomerRepository {

    @Autowired
    JdbcTemplate template;

    @Autowired
    LocationsService locationsService;

    public List<Customer> getCustomers() {
        String sql = "SELECT * FROM customers";

        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            Customer foundCustomer = rowMapper.mapRow(rs, rowNum);

            foundCustomer.setAddress(locationsService.getAddressById(rs.getInt(4)));

            return foundCustomer;
        });
    }


    public Customer getCustomerById(int id) {
        String sql = "SELECT * FROM customers WHERE CustomerId = ?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);

        List<Customer> customers = template.query(sql, (ResultSet rs, int rowNum) -> {
            Customer foundCustomer = rowMapper.mapRow(rs, rowNum);

            foundCustomer.setAddress(locationsService.getAddressById(rs.getInt(4)));

            return foundCustomer;
        }, id);

        if (customers.size() <= 0) {
            return null;
        }
        else {
            return customers.get(0);
        }
    }

    public int getCount() {
        String sql = "SELECT COUNT(CustomerId) FROM customers";

        return template.queryForObject(sql, Integer.class);
    }

    public int getCountWithWhereClause(String whereClause) {
        String sql = "SELECT COUNT(CustomerId) FROM customers WHERE " + whereClause;

        return template.queryForObject(sql, Integer.class);
    }

    public void insertCustomer(Customer customer) {
        String sql = "INSERT INTO customer VALUES (DEFAULT, ?, ? ,?, ?, ?, DEFAULT)";

        template.update(sql, customer.getFirstName(), customer.getLastName(),
                customer.getAddress().getAddressId(), customer.isIdentityValidated(), customer.isCreditValidated());
    }

    public void updateCustomer(int id, Customer customer) {
        String sql = "UPDATE customers SET FirstName = ?, LastName = ?, AddressId = ?, IdentityValidated = ?, CreditValidated = ? WHERE CustomerId = ?";

        template.update(sql, customer.getFirstName(), customer.getLastName(),
                customer.getAddress().getAddressId(), customer.isIdentityValidated(), customer.isCreditValidated(), id);
    }
}
