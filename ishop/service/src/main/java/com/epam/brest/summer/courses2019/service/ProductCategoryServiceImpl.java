package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.dao.ProductCategoryDao;
import com.epam.brest.summer.courses2019.model.ProductCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    /**
     * Default logger for current class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCategoryServiceImpl.class);

    /**
     * Data access object for work with DataBase
     */
    private final ProductCategoryDao productCategoryDao;

    /**
     * Constructs new object with given data access object
     *
     * @param productCategoryDao product category data access object
     */
    @Autowired
    public ProductCategoryServiceImpl(ProductCategoryDao productCategoryDao) {
        this.productCategoryDao = productCategoryDao;
    }

    /**
     * Returns all products categories
     * @return {@code List} with all {@code product categories}
     */
    @Override
    public List<ProductCategory> findAll() {

        LOGGER.debug("Find all Product Categories()");

        return productCategoryDao.findAll();
    }

    /**
     * Returns {@code Product category} with specified id
     *
     * @param productCategoryId product category id to find {@code product category} by
     * @return {@code} Product category that fits specified id
     */
    @Override
    public ProductCategory findProductCategoryById(Integer productCategoryId) {

        LOGGER.debug("Find Product category by Id({})", productCategoryId);

        return productCategoryDao.findProductCategoryById(productCategoryId)
                .orElseThrow(() -> new RuntimeException("Failed to get Product Category from DataBase!"));
    }

    /**
     * Adds new product category to DataBase
     *
     * @param productCategory {@code product category} to save
     * @return new Product category with generated id
     */
    @Override
    public ProductCategory add(ProductCategory productCategory) {

        LOGGER.debug("Add new Product category({})", productCategory);

        return productCategoryDao.add(productCategory);
    }

    /**
     * Updates existing {@code product category} with new one
     *
     * @param productCategory {@code product category} to update existing one
     */
    @Override
    public void update(ProductCategory productCategory) {

        LOGGER.debug("update({})", productCategory);

        productCategoryDao.update(productCategory);
    }

    /**
     * Deletes {@code Category} with specified id
     *
     * @param productCategoryId category id to delete by
     */
    @Override
    public void delete(Integer productCategoryId) {

        LOGGER.debug("delete({})", productCategoryId);

        productCategoryDao.delete(productCategoryId);
    }
}
