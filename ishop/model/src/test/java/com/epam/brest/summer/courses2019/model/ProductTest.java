package com.epam.brest.summer.courses2019.model;

import org.junit.Assert;
import org.junit.Test;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductTest {

    Product product = new Product();

    @Test
    public void getProductId() {
        product.setProductId(11);
        Assert.assertTrue(product.getProductId().equals(11));
    }

    @Test
    public void getProductName() {
        product.setProductName("Samsung galaxy s8 plus g955fe");
        Assert.assertTrue(product.getProductName().equals("Samsung galaxy s8 plus g955fe"));
    }

    @Test
    public void getProductCategoryId() {
        product.setProductCategoryId(1);
        Assert.assertTrue(product.getProductCategoryId().equals(1));
    }

    @Test
    public void getReceiptDate() {
        product.setProductReceiptDate(LocalDate.of(2018, 12, 28));
        Assert.assertTrue(product.getProductReceiptDate().equals(LocalDate.of(2018, 12, 28)));
    }

    @Test
    public void getProductQuantity() {
        product.setProductQuantity(new BigDecimal("8"));
        Assert.assertTrue(product.getProductQuantity().compareTo(new BigDecimal("8")) == 0);
    }

    @Test
    public void getProductPrice() {
        product.setProductPrice(new BigDecimal("350"));
        Assert.assertTrue(product.getProductPrice().compareTo(new BigDecimal("350")) == 0);
    }
}
