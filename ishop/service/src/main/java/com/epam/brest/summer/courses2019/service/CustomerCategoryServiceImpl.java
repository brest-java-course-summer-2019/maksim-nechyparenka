package com.epam.brest.summer.courses2019.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Customer Category Service Interface implementation.
 */

@Component
@Transactional
public class CustomerCategoryServiceImpl implements CustomerCategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    CustomerCategoryDao customerCategoryDao;

    public CustomerCategoryServiceImpl(CustomerCategoryDao customerCategoryDao) {
        this.customerCategoryDao = customerCategoryDao;
    }

    @Override
    public CustomerCategory add(CustomerCategory customerCategory) {
        LOGGER.debug("Add new customer category: ({})", customerCategory);
        return customerCategoryDao.add(customerCategory);
    }

    @Override
    public List<CustomerCategory> findAll() {
        LOGGER.debug("Find all customer categories: ({})");
        return customerCategoryDao.findAll();
    }

    @Override
    public Optional<CustomerCategory> findCustomerCategoryById(Integer customerCategoryId) {
        LOGGER.debug("Find customer category by id: ({})", customerCategoryId);
        return customerCategoryDao.findCustomerCategoryById(customerCategoryId);
    }

    @Override
    public void update(CustomerCategory customerCategory) {
        LOGGER.debug("Update existing customer category: ({})", customerCategory);
        customerCategoryDao.update(customerCategory);
    }

    @Override
    public void delete(Integer customerCategoryId) {
        LOGGER.debug("Delete existing customer category: ({})", customerCategoryId);
        customerCategoryDao.delete(customerCategoryId);
    }
}
