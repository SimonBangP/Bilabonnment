package com.example.bilabonnmenteksamensprojekt.repositories;

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

}
