package com.epam.brest.summer.courses2019.model.stub;

import com.epam.brest.summer.courses2019.model.stub.ProductStub;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ProductStubTest {

    ProductStub productStub = new ProductStub();

    @Test
    public void getProductId() {
        productStub.setProductId(11);
        Assert.assertTrue(productStub.getProductId().equals(11));
    }

    @Test
    public void getProductName() {
        productStub.setProductName("Samsung galaxy s8 plus g955fe");
        Assert.assertTrue(productStub.getProductName().equals("Samsung galaxy s8 plus g955fe"));
    }

    @Test
    public void getProductPrice() {
        productStub.setProductPrice(new BigDecimal("350"));
        Assert.assertTrue(productStub.getProductPrice().compareTo(new BigDecimal("350")) == 0);
    }
}
