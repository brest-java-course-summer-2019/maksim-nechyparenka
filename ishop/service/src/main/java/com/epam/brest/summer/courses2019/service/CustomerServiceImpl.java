package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.dao.CustomerDao;
import com.epam.brest.summer.courses2019.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Customer Service Interface implementation.
 */

@Component
@Transactional
public class CustomerServiceImpl  implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private CustomerDao customerDao;

    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public List<Customer> findAll() {
        LOGGER.debug("Find all Customers!");
        return customerDao.findAll();
    }

    @Override
    public List<Customer> findByCustomerCategoryId(Integer customerCategoryId) {
        LOGGER.debug("findByCustomerCategoryId({})", customerCategoryId);
        return customerDao.findByCustomerCategoryId(customerCategoryId);
    }

    @Override
    public Optional<Customer> findById(Integer customerId) {
        LOGGER.debug("findById({})", customerId);
        return Optional.of(customerDao.findById(customerId).orElseThrow(()
                -> new RuntimeException("Failed to get Customer from DataBase!")));
    }

    @Override
    public Customer add(Customer customer) {
        LOGGER.debug("add({})", customer);
        return customerDao.add(customer);
    }

    @Override
    public void update(Customer customer) {
        LOGGER.debug("update({})", customer);
        customerDao.update(customer);
    }

    @Override
    public void delete(Integer customerId) {
        LOGGER.debug("delete({})", customerId);
        customerDao.delete(customerId);
    }
}
