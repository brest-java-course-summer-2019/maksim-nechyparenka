package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Product;
import com.epam.brest.summer.courses2019.model.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductDao {

    Product add(Product product);

    void update(Product product);

    void delete(Integer productId);

    List<Product> findAll();

    /**
     * Returns all DataTransferObjects(DTO) of products found in DataBase.
     *
     * @return Product Data Transfer Objects as {@code List}
     */
    List<ProductDTO> findAllProductDTOs();

    Optional<Product> findById(Integer productId);

    List<Product> findByProductCategoryId(Integer productCategoryId);

    /**
     * Get balance of Product by ID.
     *
     * @return Product quantity.
     */
    BigDecimal findBalanceById(Integer productId);
}
