package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml"})
@Transactional
@Rollback
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    public void findAll() {
        List<Product> products = productService.findAll();

        assertNotNull(products);
        assertFalse(products.isEmpty());
    }

    @Test
    public void findBalanceById() {
        int id = 1;
        List<Product> products = productService.findAll();

        assertNotNull(products);
        assertEquals("8", products.get(id).getProductQuantity());

    }

    @Test
    public void findById() {
        int id = 1;
        Product product = productService.findById(id);

        assertNotNull(product);
        assertEquals("Samsung galaxy s8 plus g955f", product.getProductName());
    }

    @Test
    public void add() {
        long count = productService.findAll().size();
        assertThrows(DuplicateKeyException.class, () -> productService.add(create(), create()));
        long newCount = productService.findAll().size();
        assertEquals(count, newCount);
    }

    @Test
    public void update() {
        int id = 2;
        Product product = create();
        product.setProductCategoryId(id);
        productService.update(product);
        product = productService.findById(id);

        assertNotNull(product);
        assertEquals("name", product.getProductName());
    }

    @Test
    public void delete() {
        int id = 3;
        productService.delete(id);
        assertThrows(RuntimeException.class, () -> productService.findById(id));
    }

    private Product create() {
        Product product = new Product();
        product.setProductName("name");
        return product;
    }
}
