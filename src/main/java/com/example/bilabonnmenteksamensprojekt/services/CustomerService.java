package com.example.bilabonnmenteksamensprojekt.services;

import com.example.bilabonnmenteksamensprojekt.models.customers.Customer;
import com.example.bilabonnmenteksamensprojekt.models.locations.Address;
import com.example.bilabonnmenteksamensprojekt.repositories.CustomerRepository;
import com.example.bilabonnmenteksamensprojekt.repositories.LocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements SearchableAlarmService{

    @Autowired
    CustomerRepository repository;
    LocationsRepository locationsRepository;
    public Customer getCustomerById(int id) {
        return repository.getCustomerById(id);
    }

    public List<Customer> getCustomers(){
        return repository.getCustomers();
    }

    public void insertCustomer(Customer customer) {
        repository.insertCustomer(customer);
    }

    public void updateCustomer(int id, Customer customer) {
        repository.updateCustomer(id, customer);
    }

    @Override
    public int getCount() {
        return repository.getCount();
    }

    @Override
    public int getCountWithWhereClause(String whereClause) {
        return repository.getCountWithWhereClause(whereClause);
    }
}
