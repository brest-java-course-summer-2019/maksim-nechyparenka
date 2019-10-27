package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.model.ProductCategory;
import java.util.List;

/**
 * Service methods for work with {@code Product categories}
 *
 * @see ProductCategory
 * @author Maksim Nechyparenka
 */
public interface ProductCategoryService {

    /**
     * Get all products categories
     * @return {@code List} with all {@code product categories}
     */
    List<ProductCategory> findAll();

    /**
     * Returns {@code product category} with given id
     *
     * @param productCategoryId category id to find {@code product category} by
     * @return {@code product category} with specified id
     */
    ProductCategory findProductCategoryById(Integer productCategoryId);

    /**
     * Adds {@code product category} to DataBase
     *
     * @param productCategory {@code product category} to add
     */
    ProductCategory add(ProductCategory productCategory);

    /**
     * Updates already existing {@code product category} by new one
     *
     * @param productCategory {@code product category} to update existing one
     */
    void update(ProductCategory productCategory);

    /**
     * Deletes already existing {@code product category} with specified id
     *
     * @param productCategoryId product category id to delete by
     */
    void delete(Integer productCategoryId);
}
