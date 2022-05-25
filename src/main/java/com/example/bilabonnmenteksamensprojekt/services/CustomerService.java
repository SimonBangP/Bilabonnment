package com.example.bilabonnmenteksamensprojekt.services;

import com.example.bilabonnmenteksamensprojekt.models.customers.Customer;
import com.example.bilabonnmenteksamensprojekt.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository repository;

    public Customer getCustomerById(int id) {
        return repository.getCustomerById(id);
    }

    public List<Customer> getCustomers(){
        return repository.getCustomers();
    }

}
