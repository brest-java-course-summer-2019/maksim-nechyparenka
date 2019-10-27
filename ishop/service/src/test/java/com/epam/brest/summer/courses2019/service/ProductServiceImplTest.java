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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    public void findById() {
        int id = 1;
        Product product = productService.findById(id);

        assertNotNull(product);
        assertEquals("Samsung galaxy s8 plus g955f", product.getProductName());
    }

    @Test
    public void add() {
        long amount = productService.findAll().size();
        productService.add(create());
        long newAmount = productService.findAll().size();
        assertNotEquals(amount, newAmount);
    }

    @Test
    public void update() {
        int id = 3;
        Product product = create();
        productService.add(product);
        product.setProductCategoryId(id);
        productService.update(product);
        Product updatedProduct = productService.findById(product.getProductId());

        assertNotNull(updatedProduct);
        assertEquals("productNewName", updatedProduct.getProductName());
    }

    @Test
    public void delete() {
        int id = 3;
        productService.delete(id);
        assertThrows(RuntimeException.class, () -> productService.findById(id));
    }

    private Product create() {
        Product product = new Product("productNewName", 2, "productCategory",
                "productSupplier", LocalDate.of(2019,8,8), new BigDecimal("5"),
                new BigDecimal("555"));
        return product;
    }
}
