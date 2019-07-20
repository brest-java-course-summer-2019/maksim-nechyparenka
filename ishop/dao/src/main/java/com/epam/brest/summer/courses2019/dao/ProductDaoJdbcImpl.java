package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Product;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

public class ProductDaoJdbcImpl implements ProductDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Product add(Product product) {
        return null;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(Integer productId) {

    }

    @Override
    public List<Product> findAll() {
        return null;
    }
}
