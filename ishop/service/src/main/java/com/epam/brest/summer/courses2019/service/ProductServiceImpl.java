package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.dao.ProductDao;
import com.epam.brest.summer.courses2019.dao.ProductStubDao;
import com.epam.brest.summer.courses2019.model.Product;
import com.epam.brest.summer.courses2019.model.stub.ProductStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 *  Product Service Interface implementation
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private ProductDao productDao;
    private ProductStubDao productStubDao;

    public ProductServiceImpl(ProductDao productDao, ProductStubDao productStubDao) {
        this.productDao = productDao;
        this.productStubDao = productStubDao;
    }

    @Override
    public List<Product> findAll() {

        LOGGER.debug("Find all Products");

        return productDao.findAll();
    }

    @Override
    public List<ProductStub> findAllStubs() {

        LOGGER.debug("Find all ProductStubs");

        return productStubDao.findAllStubs();
    }

    @Override
    public Product findById(Integer productId) {

        LOGGER.debug("Find Product by ID: ({})", productId);

        return productDao.findById(productId)
                .orElseThrow(() -> new RuntimeException("Failed to get Product from DataBase!"));
    }

    @Override
    public ProductStub findStubById(Integer productId) {

        LOGGER.debug("Find ProductStub by ID: ({})", productId);

        return productStubDao.findStubById(productId)
                .orElseThrow(() -> new RuntimeException("Failed to get Product from DataBase!"));
    }

    @Override
    public List<Product> findByProductCategoryId(Integer productCategoryId) {

        LOGGER.debug("Find Products by product category ID: ({})", productCategoryId);

        return productDao.findByProductCategoryId(productCategoryId);
    }

    @Override
    public List<ProductStub> findStubsByProductCategoryId(Integer productCategoryId) {

        LOGGER.debug("Find Products by product category ID: ({})", productCategoryId);

        return productStubDao.findStubsByProductCategoryId(productCategoryId);
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

    @Override
    public List<ProductStub> findProductStubsFromPriceIntervalInCategory(BigDecimal priceStart,
                                                BigDecimal priceEnd, Integer productCategoryId) {

        LOGGER.debug("Find ProductStubs from price interval in category({}, {}, {})",
                priceStart, priceEnd, productCategoryId);

        return productStubDao.findProductStubsFromPriceIntervalInCategory(priceStart, priceEnd, productCategoryId);
    }
}
