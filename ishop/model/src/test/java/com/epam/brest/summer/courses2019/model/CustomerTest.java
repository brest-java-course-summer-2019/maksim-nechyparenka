package com.epam.brest.summer.courses2019.model;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {

    Customer customer = new Customer();

    @Test
    public void getCustomerId() {
        customer.setCustomerId(8);
        Assert.assertTrue(customer.getCustomerId().equals(8));
    }

    @Test
    public void getCustomerFirstName() {
        customer.setCustomerFirstName("John");
        Assert.assertTrue(customer.getCustomerFirstName().equals("John"));
    }

    @Test
    public void getCustomerLastName() {
        customer.setCustomerLastName("Johnson");
        Assert.assertTrue(customer.getCustomerLastName().equals("Johnson"));
    }

    @Test
    public void getRegistrationDate() {
        customer.setRegistrationDate("08-08-2019");
        Assert.assertTrue(customer.getRegistrationDate().equals("08-08-2019"));
    }

    @Test
    public void getCustomerLogin() {
        customer.setCustomerLogin("login");
        Assert.assertTrue(customer.getCustomerLogin().equals("login"));
    }

    @Test
    public void getCustomerPassword() {
        customer.setCustomerPassword("password");
        Assert.assertTrue(customer.getCustomerPassword().equals("password"));
    }

    @Test
    public void getCustomerCardNumber() {
        customer.setCustomerCardNumber("8888 8888 8888 8888");
        Assert.assertTrue(customer.getCustomerCardNumber().equals("8888 8888 8888 8888"));
    }
}
