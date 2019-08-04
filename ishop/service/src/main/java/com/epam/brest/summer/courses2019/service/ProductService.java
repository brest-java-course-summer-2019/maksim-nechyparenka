package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    /**
     * Find all Products in catalog.
     *
     * @return Products.
     */
    List<Product> findAll();

    /**
     * Get balance of Product by ID.
     *
     * @return Product quantity.
     */
    BigDecimal findBalanceById(Integer productId);

    /**
     * Find Product By Id.
     *
     * @param id id
     * @return Product
     */
    Product findById(Integer id);

    /**
     * Update Product.
     *
     * @param product product
     */
    void update(Product product);

    /**
     * Delete Product.
     *
     * @param id product id
     */
    void delete(int id);
}
