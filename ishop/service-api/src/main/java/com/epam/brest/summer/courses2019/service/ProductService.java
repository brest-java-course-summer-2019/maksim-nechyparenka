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
     * Find Product By Id.
     *
     * @param id id
     * @return Product
     */
    Product findById(Integer id);

    /**
     * Find Products By category Id.
     *
     * @param productCategoryId productCategoryid
     * @return Products
     */
    List<Product> findByProductCategoryId(Integer productCategoryId);

    /**
     * Persist new product.
     *
     * @param products products
     * @return product
     */
    void add(Product... products);

    Product add(Product product);

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
    void delete(Integer id);
}
