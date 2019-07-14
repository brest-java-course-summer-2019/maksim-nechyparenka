package com.epam.brest.summer.courses2019.model;

import org.junit.Assert;
import org.junit.Test;
import java.math.BigDecimal;

public class ProductTest {

    Product product = new Product();

    @Test
    public void getProductId() {
        product.setProductId(11);
        Assert.assertTrue(product.getProductId().equals(11));
    }

    @Test
    public void getProductName() {
        product.setProductName("Cell phone");
        Assert.assertTrue(product.getProductName().equals("Cell phone"));
    }

    @Test
    public void getProductCategory() {
        product.setProductCategory("Cell phones and Accessories");
        Assert.assertTrue(product.getProductCategory().equals("Cell phones and Accessories"));
    }

    @Test
    public void getReceiptDate() {
        product.setReceiptDate("28.12.2018");
        Assert.assertTrue(product.getReceiptDate().equals("28.12.2018"));
    }

    @Test
    public void getProductQuantity() {
        product.setProductQuantity(new BigDecimal("7"));
        Assert.assertTrue(product.getProductQuantity().compareTo(new BigDecimal("7")) == 0);
    }

    @Test
    public void getProductPrice() {
        product.setProductPrice(new BigDecimal("77"));
        Assert.assertTrue(product.getProductPrice().compareTo(new BigDecimal("77")) == 0);
    }
}
