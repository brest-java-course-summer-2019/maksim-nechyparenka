package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.CustomerCategory;

import java.util.List;
import java.util.Optional;

public interface CustomerCategoryDao {

    /**
     * Get all customer categories
     * @return
     */

    List<CustomerCategory> findAll();

    /**
     * Get customer category by id
     * @param customerCategoryId
     * @return
     */

    Optional<CustomerCategory> findCustomerCategoryById(Integer customerCategoryId);

    /**
     * Create new customer category
     * @param customerCategory
     * @return
     */

    CustomerCategory add(CustomerCategory customerCategory);

    /**
     * Update customer category
     * @param customerCategory
     */

    void update(CustomerCategory customerCategory);

    /**
     * Delete customer category
     * @param customerCategoryId
     */

    void delete(Integer customerCategoryId);
}
