package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {

    List<Customer> findAll();

    Optional<Customer> findByCustomerCategoryId(Integer customerCategoryId);

    Optional<Customer> findById(Integer customerId);

    Customer add(Customer customer);

    void update(Customer customer);

    void delete(Integer customerId);
}
