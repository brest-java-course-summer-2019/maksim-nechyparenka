package com.epam.brest.summer.courses2019.model.dto;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductDtoTest {

    ProductDTO productDTO = new ProductDTO();

    @Test
    public void getProductId() {
        productDTO.setProductId(11);
        Assert.assertTrue(productDTO.getProductId().equals(11));
    }

    @Test
    public void getProductName() {
        productDTO.setProductName("Samsung galaxy s8 plus g955fe");
        Assert.assertTrue(productDTO.getProductName().equals("Samsung galaxy s8 plus g955fe"));
    }

    @Test
    public void getProductPrice() {
        productDTO.setProductPrice(new BigDecimal("350"));
        Assert.assertTrue(productDTO.getProductPrice().compareTo(new BigDecimal("350")) == 0);
    }
}
