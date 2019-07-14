package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Product;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class ProductDaoJdbcImplTest {

    ProductDao productDao = new ProductDaoJdbcImpl();

    @Test
    void findAll() {
        List<Product> products = productDao.findAll();
        Assert.assertNotNull(products);
        Assert.assertTrue(products.size() > 0);
    }
}