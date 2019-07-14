package com.epam.brest.summer.courses2019.model;

import org.junit.Assert;

import java.math.BigDecimal;

public class ProductTest {

    Product product = new Product();

    public void getProductId() {
        product.setProductId(11);
        Assert.assertTrue(product.getProductId().equals(11));
    }

    public void getProductName() {
        product.setProductName("Cell phone");
        Assert.assertTrue(product.getProductName().equals("Cell phone"));
    }

    public void getProductCategory() {
        product.setProductCategory("Cell phones and Accessories");
        Assert.assertTrue(product.getProductCategory().equals("Cell phones and Accessories"));
    }

    public void getReceiptDate() {
        product.setReceiptDate("28.12.2018");
        Assert.assertTrue(product.getReceiptDate().equals("28.12.2018"));
    }

    public void getProductQuantity() {
        product.setProductQuantity(new BigDecimal("7"));
        Assert.assertTrue(product.getProductQuantity().equals(new BigDecimal("7")));
    }

    public void getProductPrice() {
        product.setProductPrice(new BigDecimal("77"));
        Assert.assertTrue(product.getProductPrice().equals(new BigDecimal("77")));
    }
}
