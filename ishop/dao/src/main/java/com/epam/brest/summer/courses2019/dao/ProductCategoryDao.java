package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryDao {

    /**
     * Get all product categories
     * @return
     */

    List<ProductCategory> findAll();

    /**
     * Get product category by id
     * @param productCategoryId
     * @return
     */

    Optional<ProductCategory> findProductCategoryById(Integer productCategoryId);

    /**
     * Create new product category
     * @param productCategory
     * @return
     */

    ProductCategory add(ProductCategory productCategory);

    /**
     * Update product category
     * @param productCategory
     */

    void update(ProductCategory productCategory);

    /**
     * Delete product category
     * @param productCategoryId
     */

    void delete(Integer productCategoryId);
}
