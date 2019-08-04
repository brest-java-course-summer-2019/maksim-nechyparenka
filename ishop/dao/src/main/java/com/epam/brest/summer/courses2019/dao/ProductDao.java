package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductDao {

    Product add(Product product);

    void update(Product product);

    void delete(Integer productId);

    List<Product> findAll();

    Product findById(Integer productId);

    /**
     * Get balance of Product by ID.
     *
     * @return Product quantity.
     */
    BigDecimal findBalanceById(Integer productId);
}
