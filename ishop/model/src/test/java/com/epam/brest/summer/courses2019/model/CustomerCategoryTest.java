package com.epam.brest.summer.courses2019.model;

import org.junit.Assert;
import org.junit.Test;

public class CustomerCategoryTest {

    CustomerCategory customerCategory = new CustomerCategory();

    @Test
    public void getCustomerCategoryId() {
        customerCategory.setCustomerCategoryId(1);
        Assert.assertTrue(customerCategory.getCustomerCategoryId().equals(1));
    }

    @Test
    public void getCustomerCategoryName() {
        customerCategory.setCustomerCategoryName("Normal");
        Assert.assertTrue(customerCategory.getCustomerCategoryName().equals("Normal"));
    }
}
