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
        LOGGER.debug("Find all Products!");
        return productDao.findAll();
    }

    @Override
    public Product findById(Integer productId) {
        LOGGER.debug("Find Product by ID: ({})", productId);
        return productDao.findById(productId)
                .orElseThrow(() -> new RuntimeException("Failed to get Product from DataBase!"));
    }

    @Override
    public List<Product> findByProductCategoryId(Integer productCategoryId) {
        LOGGER.debug("Find Products by product category ID: ({})", productCategoryId);
        return productDao.findByProductCategoryId(productCategoryId);
    }

    @Override
    public void add(Product... products) {
        LOGGER.debug("Add new Products: ({})", products);
        for (Product product : products) {
            productDao.add(product);
        }
    }

    @Override
    public Product add(Product product) {
        LOGGER.debug("Add new Product: ({})", product);
        return productDao.add(product);
    }

    @Override
    public void update(Product product) {
        LOGGER.debug("Update existing Product: ({})", product);
        productDao.update(product);
    }

    @Override
    public void delete(Integer id) {
        LOGGER.debug("Delete existing Product: ({})", id);
        productDao.delete(id);
    }
}
