package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Product;


import java.util.List;
import java.util.Optional;

public interface ProductDao {

    /**
     * Save product to database. Returns inserted Product with generated id.
     *
     * @param product Product object to save in database
     * @return {@code Optional} Product with generated id
     */
    Product add(Product product);

    /**
     * Update already existing product object by new object.
     *
     * @param product Object to replace older
     */
    void update(Product product);

    /**
     * Delete product from database by defined id.
     *
     * @param productId Product id to delete
     */
    void delete(Integer productId);

    /**
     * Returns all products found in DataBase.
     *
     * @return Products as {@code List}
     */
    List<Product> findAll();

    /**
     * Returns product with defined id.
     *
     * @param productId Id of product to find
     * @return {@code Optional} describing the value of product found
     */
    Optional<Product> findById(Integer productId);

    /**
     * Returns all products found in DataBase by category id.
     *
     * @return Products as {@code List}
     */
    List<Product> findByProductCategoryId(Integer productCategoryId);

}
