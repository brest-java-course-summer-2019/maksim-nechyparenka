package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Product;

import java.util.List;

public interface ProductDao {

    Product add(Product product);

    void update(Product product);

    void delete(Integer productId);

    List<Product> findAll();
}
