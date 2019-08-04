package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.dao.ProductDao;
import com.epam.brest.summer.courses2019.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 *  Product Service Interface implementation.
 */

public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private ProductDao dao;

    public ProductServiceImpl(ProductDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Product> findAll() {
        LOGGER.debug("Find all Products!");
        return dao.findAll();
    }

    @Override
    public BigDecimal findBalanceById(Integer productId) {
        LOGGER.debug("Find Product balance by Product ID!");
        return dao.findBalanceById(productId);
    }

    @Override
    public Product findById(Integer id) {
        LOGGER.debug("findById({})", id);
        return dao.findById(id);
                //.orElseThrow(() -> new RuntimeException("Failed to get Product from DataBase!")));
    }

    @Override
    public void update(Product product) {
        LOGGER.debug("update({})", product);
        dao.update(product);
    }

    @Override
    public void delete(int id) {
        LOGGER.debug("delete({})", id);
        dao.delete(id);
    }

}
