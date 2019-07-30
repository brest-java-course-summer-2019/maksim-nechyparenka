package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
public class ProductDaoJdbcImplTest {

    private static final String CELLPHONE = "Samsung";

    @Autowired
    ProductDao productDao;

    @Test
    public void findAll() {
        List<Product> products = productDao.findAll();
        Assert.assertNotNull(products);
        Assert.assertTrue(products.size() > 0);
    }

    @Test
    public void getProductById() {
        Product product = productDao.findById(1).get();
        assertNotNull(product);
        assertTrue(product.getProductId().equals(1));
        assertTrue(product.getProductName().equals(CELLPHONE));
    }

    @Test
    public void addProduct() {
        Product testProduct = new Product();
        testProduct.setProductName("Samsung");
        testProduct.setCategoryId(1);
        testProduct.setReceiptDate("28.07.2019");
        testProduct.setProductQuantity(new BigDecimal("5"));
        testProduct.setProductPrice(new BigDecimal("555.55"));
        Product newProduct = productDao.add(testProduct);
        Assert.assertNotNull(newProduct.getProductId());
    }

    @Test
    public void updateProduct() {
        Product newProduct = new Product(CELLPHONE);
        newProduct = productDao.add(newProduct);
        newProduct.setProductName(CELLPHONE);
        productDao.update(newProduct);
        Product updatedProduct = productDao.findById(newProduct.getProductId()).get();
        assertTrue(newProduct.getProductId().equals(updatedProduct.getProductId()));
        assertTrue(newProduct.getProductName().equals(updatedProduct.getProductName()));
    }

    @Test
    public void deleteProduct() {
        Product product = new Product(CELLPHONE);
        product = productDao.add(product);
        List<Product> products = productDao.findAll();
        int sizeBefore = products.size();
        productDao.delete(product.getProductId());
        assertTrue((sizeBefore - 1) == productDao.findAll().size());
    }
}