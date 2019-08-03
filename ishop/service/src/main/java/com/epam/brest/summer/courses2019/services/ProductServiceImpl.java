package com.epam.brest.summer.courses2019.services;

import com.epam.brest.summer.courses2019.dao.ProductDao;
import com.epam.brest.summer.courses2019.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

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
    public List<Product> findBalanceById() {
        LOGGER.debug("Find Product balance by Product ID!");
        return dao.findBalanceById();
    }

    @Override
    public Product findById(Integer id) {
        LOGGER.debug("findById({})", id);
        return dao.findById(id)
                .orElseThrow(() -> new RuntimeException("Failed to get Product from DataBase!"));
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
