package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
@Transactional
@Rollback
public class CustomerDaoJdbcImplTest {

    @Autowired
    CustomerDao customerDao;

    @Test
    public void findAll() {
        List<Customer> customers = customerDao.findAll();
        Assert.assertNotNull(customers);
        Assert.assertTrue(customers.size() > 0);
    }

    @Test
    public void findById() {
        assertNotNull(customerDao);
        Customer customer = customerDao.findById(1).get();
        assertTrue(customer.getCustomerId().equals(1));
        assertTrue(customer.getCustomerFirstName().equals("Danila"));
        assertTrue(customer.getCustomerLastName().equals("Kozlovsky"));
        assertTrue(customer.getCustomerRegistrationDate().equals(LocalDate.of(2018, 8, 8)));
        assertTrue(customer.getCustomerLogin().equals("login01"));
        assertTrue(customer.getCustomerPassword().equals("password01"));
        assertTrue(customer.getCustomerCardNumber().equals("1234 5678 9012 0001"));
        assertTrue(customer.getCustomerCategoryId().equals(1));
    }

    @Test
    public void findByCustomerCategoryId() {
        List<Customer> customers = customerDao.findAll();
        assertNotNull(customers);
        List<Customer> result = customerDao.findByCustomerCategoryId(1);
        assertNotNull(result);
    }

    @Test
    public void addCustomer() {
        List<Customer> customers = customerDao.findAll();
        int sizeBefore = customers.size();

        Customer testCustomer = new Customer("firstName5", "lastName5",
                LocalDate.of(2019, 8, 9), "login05", "password05",
                "cardNum05", 1);
        Customer newCustomer = customerDao.add(testCustomer);

        assertNotNull(newCustomer.getCustomerId());
        assertTrue(newCustomer.getCustomerFirstName().equals(testCustomer.getCustomerFirstName()));
        assertTrue(newCustomer.getCustomerLastName().equals(testCustomer.getCustomerLastName()));
        assertTrue(newCustomer.getCustomerRegistrationDate().equals(testCustomer.getCustomerRegistrationDate()));
        assertTrue(newCustomer.getCustomerLogin().equals(testCustomer.getCustomerLogin()));
        assertTrue(newCustomer.getCustomerPassword().equals(testCustomer.getCustomerPassword()));
        assertTrue(newCustomer.getCustomerCardNumber().equals(testCustomer.getCustomerCardNumber()));
        assertTrue(newCustomer.getCustomerCategoryId().equals(testCustomer.getCustomerCategoryId()));
        assertTrue((sizeBefore + 1) == customerDao.findAll().size());
    }

    @Test
    public void updateCustomer() {
        Customer customer = customerDao.findById(3).get();
        customer.setCustomerFirstName("newFirstName");
        customer.setCustomerLastName("newLastName");
        customer.setCustomerRegistrationDate(LocalDate.of(2019, 8, 9));
        customer.setCustomerLogin("newLogin");
        customer.setCustomerPassword("newPassword");
        customer.setCustomerCardNumber("1111-1111-1111-1111");
        customer.setCustomerCategoryId(2);

        customerDao.update(customer);
        Customer updatedCustomer = customerDao.findById(customer.getCustomerId()).get();
        assertTrue(updatedCustomer.getCustomerId().equals(customer.getCustomerId()));
        assertTrue(updatedCustomer.getCustomerFirstName().equals(customer.getCustomerFirstName()));
        assertTrue(updatedCustomer.getCustomerLastName().equals(customer.getCustomerLastName()));
        assertTrue(updatedCustomer.getCustomerRegistrationDate().equals(customer.getCustomerRegistrationDate()));
        assertTrue(updatedCustomer.getCustomerLogin().equals(customer.getCustomerLogin()));
        assertTrue(updatedCustomer.getCustomerPassword().equals(customer.getCustomerPassword()));
        assertTrue(updatedCustomer.getCustomerCardNumber().equals(customer.getCustomerCardNumber()));
        assertTrue(updatedCustomer.getCustomerCategoryId().equals(customer.getCustomerCategoryId()));
    }

    @Test
    public void deleteCustomer() {
        Customer customer = new Customer("firstName6", "lastName6",
                LocalDate.of(2019, 8, 9), "login06", "password06",
                "cardNum06", 1);
        customerDao.add(customer);
        List<Customer> customers = customerDao.findAll();
        int sizeBefore = customers.size();
        customerDao.delete(customer.getCustomerId());
        assertTrue((sizeBefore - 1) == customerDao.findAll().size());
    }
}
