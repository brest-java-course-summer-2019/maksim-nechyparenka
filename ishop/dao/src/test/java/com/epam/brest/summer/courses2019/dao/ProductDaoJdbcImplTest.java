package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
@Transactional
@Rollback
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
    public void findById() {
        assertNotNull(productDao);
        Product product = productDao.findById(1).get();
        assertTrue(product.getProductId().equals(1));
        assertTrue(product.getProductName().equals("Samsung galaxy s8 plus g955f"));
        assertTrue(product.getProductCategoryId().equals(1));
        assertEquals(new BigDecimal("8"), product.getProductQuantity());
        assertEquals(new BigDecimal("350"), product.getProductPrice());
    }

    @Test
    public void addProduct() {
        Product testProduct = new Product();
        testProduct.setProductName("Samsung");
        testProduct.setProductCategoryId(1);
        testProduct.setProductReceiptDate("28.07.2019");
        testProduct.setProductQuantity(new BigDecimal("5"));
        testProduct.setProductPrice(new BigDecimal("555.55"));
        Product newProduct = productDao.add(testProduct);
        Assert.assertNotNull(newProduct.getProductId());
    }

    @Test
    public void updateProduct() {
        Product product = productDao.findById(3).get();
        product.setProductName("Samsung");
        product.setProductCategoryId(1);
        product.setProductReceiptDate("28.07.2019");
        product.setProductQuantity(new BigDecimal("5"));
        product.setProductPrice(new BigDecimal("555.55"));
        productDao.update(product);

        Product updatedProduct = productDao.findById(product.getProductId()).get();

        assertTrue(updatedProduct.getProductId().equals(updatedProduct.getProductId()));
        assertTrue(updatedProduct.getProductName().equals(updatedProduct.getProductName()));
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