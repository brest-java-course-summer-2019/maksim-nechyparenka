package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.model.Product;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testng.annotations.Test;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testng.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml"})
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    void findAll() {
        List<Product> products = productService.findAll();

        assertNotNull(products);
        assertFalse(products.isEmpty());
    }

    @Test
    void findBalanceById() {
        int id = 1;
        List<Product> products = productService.findAll();

        assertNotNull(products);
        assertEquals("8", products.get(id).getProductQuantity());

    }

    @Test
    void findById() {
        int id = 1;
        Product product = productService.findById(id);

        assertNotNull(product);
        assertEquals("Samsung galaxy s8 plus g955f", product.getProductName());
    }

    @Test
    void update() {
        int id = 2;
        Product product = create();
        product.setProductCategoryId(id);
        productService.update(product);
        product = productService.findById(id);

        assertNotNull(product);
        assertEquals("name", product.getProductName());
    }

    @Test
    void delete() {
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
