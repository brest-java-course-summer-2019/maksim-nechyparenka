package com.epam.brest.summer.courses2019.model;

import org.junit.Assert;
import org.junit.Test;

public class ProductCategoryTest {

    ProductCategory productCategory = new ProductCategory();

    @Test
    public void getProductCategoryId() {
        productCategory.setProductCategoryId(1);
        Assert.assertTrue(productCategory.getProductCategoryId().equals(1));
    }

    @Test
    public void getProductCategoryName() {
        productCategory.setProductCategoryName("Cell phones & Accessories");
        Assert.assertTrue(productCategory.getProductCategoryName().equals("Cell phones & Accessories"));
    }
}
