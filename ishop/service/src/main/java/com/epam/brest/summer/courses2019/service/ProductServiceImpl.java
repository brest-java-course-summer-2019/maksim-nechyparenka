package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.dao.ProductDao;
import com.epam.brest.summer.courses2019.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 *  Product Service Interface implementation
 */
@Component
@Transactional
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> findAll() {
        LOGGER.debug("findAll({})");
        return productDao.findAll();
    }

    @Override
    public BigDecimal findBalanceById(Integer productId) {
        LOGGER.debug("findBalanceById({})", productId);
        return productDao.findBalanceById(productId);
    }

    @Override
    public Product findById(Integer productId) {
        LOGGER.debug("findById({})", productId);
        return productDao.findById(productId)
                .orElseThrow(() -> new RuntimeException("Failed to get Product from DataBase!"));
    }

    @Override
    public Product add(Product product) {
        LOGGER.debug("add({})", product);
        return productDao.add(product);
    }

    @Override
    public void update(Product product) {
        LOGGER.debug("update({})", product);
        productDao.update(product);
    }

    @Override
    public void delete(int id) {
        LOGGER.debug("delete({})", id);
        productDao.delete(id);
    }
}
