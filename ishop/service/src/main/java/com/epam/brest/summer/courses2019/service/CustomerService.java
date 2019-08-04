package com.epam.brest.summer.courses2019.service;


import com.epam.brest.summer.courses2019.model.Customer;

import java.util.List;
import java.util.Optional;

/**
 * Customer Service Interface.
 */

public interface CustomerService {

    /**
     * Get all Customers.
     *
     * @return list of all customers
     */
    List<Customer> findAll();

    /**
     * Get all Customers with specified customer category id.
     *
     * @param customerCategoryId category id
     * @return list of customers by customer category id
     */
    List<Customer> findByCustomerCategoryId(Integer customerCategoryId);

    /**
     * Get Customer with specified id.
     *
     * @param customerId customer id
     * @return customer by id
     */
    Optional<Customer> findById(Integer customerId);

    /**
     * Persist new customer.
     *
     * @param customer customer
     * @return customer
     */
    Customer add(Customer customer);

    /**
     * Update customer.
     *
     * @param customer customer
     */
    void update(Customer customer);

    /**
     * Delete customer with specified id.
     *
     * @param customerId customer id
     */
    void delete(Integer customerId);

}
