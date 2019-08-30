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
import java.time.LocalDate;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
@Transactional
@Rollback
public class ProductDaoJdbcImplTest {

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
        assertEquals(new BigDecimal("8.00"), product.getProductQuantity());
        assertEquals(new BigDecimal("350.00"), product.getProductPrice());
    }

    @Test
    public void addProduct() {
        List<Product> products = productDao.findAll();
        int sizeBefore = products.size();

        Product testProduct = new Product("Samsung", 1, LocalDate.of(2019, 7, 28),
                new BigDecimal("5"), new BigDecimal("555.55"));

        Product newProduct = productDao.add(testProduct);
        Assert.assertNotNull(newProduct.getProductId());
        assertTrue((sizeBefore + 1) == productDao.findAll().size());
    }

    @Test
    public void updateProduct() {
        Product product = productDao.findById(3).get();
        product.setProductName("Samsung");
        product.setProductCategoryId(1);
        product.setProductReceiptDate(LocalDate.of(2019, 7, 28));
        product.setProductQuantity(new BigDecimal("5.00"));
        product.setProductPrice(new BigDecimal("555.00"));
        productDao.update(product);

        Product updatedProduct = productDao.findById(product.getProductId()).get();

        assertTrue(updatedProduct.getProductId().equals(product.getProductId()));
        assertTrue(updatedProduct.getProductName().equals(product.getProductName()));
        assertTrue(updatedProduct.getProductCategoryId().equals(product.getProductCategoryId()));
        assertTrue(updatedProduct.getProductReceiptDate().equals(product.getProductReceiptDate()));
        assertTrue(updatedProduct.getProductQuantity().equals(product.getProductQuantity()));
        assertTrue(updatedProduct.getProductPrice().equals(product.getProductPrice()));
    }

    @Test
    public void deleteProduct() {
        Product product = new Product("Nokia", 1, LocalDate.of(2019, 8, 1),
                new BigDecimal("5"), new BigDecimal("350.00"));
        product = productDao.add(product);
        List<Product> products = productDao.findAll();
        int sizeBefore = products.size();
        productDao.delete(product.getProductId());
        assertTrue((sizeBefore - 1) == productDao.findAll().size());
    }
}