package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        assertNotNull(customers);
        assertTrue(customers.size() > 0);
    }

    @Test
    public void findByCustomerCategoryId() {
        List<Customer> customers = customerDao.findAll();
        assertNotNull(customers);
        Optional<Customer> result = customerDao.findByCustomerCategoryId(1);
        assertNotNull(result);
    }

    @Test
    public void findById() {
        assertNotNull(customerDao);
        Customer customer = customerDao.findById(1).get();
        assertTrue(customer.getCustomerId().equals(1));
        assertTrue(customer.getCustomerFirstName().equals("Danila"));
        assertTrue(customer.getCustomerLastName().equals("Kozlovsky"));
        assertTrue(customer.getRegistrationDate().equals("09-08-2019"));
        assertTrue(customer.getCustomerLogin().equals("login01"));
        assertTrue(customer.getCustomerPassword().equals("password01"));
        assertTrue(customer.getCustomerCardNumber().equals("1234 5678 9012 3456"));
        assertTrue(customer.getCustomerCategoryId().equals(1));
    }

    @Test
    public void add() {
        List<Customer> customers = customerDao.findAll();
        int sizeBefore = customers.size();
        Customer customer = new Customer("firstName3", "lastName3", "09-08-2019",
                "login03", "password03", "cardNum03", 1);
        Customer newCustomer = customerDao.add(customer);
        assertNotNull(newCustomer.getCustomerId());
        assertTrue(newCustomer.getCustomerFirstName().equals(customer.getCustomerFirstName()));
        assertTrue(newCustomer.getCustomerLastName().equals(customer.getCustomerLastName()));
        assertTrue(newCustomer.getRegistrationDate().equals(customer.getRegistrationDate()));
        assertTrue(newCustomer.getCustomerLogin().equals(customer.getCustomerLogin()));
        assertTrue(newCustomer.getCustomerPassword().equals(customer.getCustomerPassword()));
        assertTrue(newCustomer.getCustomerCardNumber().equals(customer.getCustomerCardNumber()));
        assertTrue(newCustomer.getCustomerCategoryId().equals(customer.getCustomerCategoryId()));
        assertTrue((sizeBefore + 1) == customerDao.findAll().size());
    }

    @Test
    public void update() {
        Customer customer = customerDao.findById(1).get();
        customer.setCustomerFirstName("newFirstName");
        customer.setCustomerLastName("newLastName");
        customer.setRegistrationDate("09-08-2019");
        customer.setCustomerLogin("newLogin");
        customer.setCustomerPassword("newPassword");
        customer.setCustomerCardNumber("1111-1111-1111-1111");
        customer.setCustomerCategoryId(1);

        customerDao.update(customer);
        Customer updatedCustomer = customerDao.findById(customer.getCustomerId()).get();
        assertTrue(updatedCustomer.getCustomerId().equals(customer.getCustomerId()));
        assertTrue(updatedCustomer.getCustomerFirstName().equals(customer.getCustomerFirstName()));
        assertTrue(updatedCustomer.getCustomerLastName().equals(customer.getCustomerLastName()));
        assertTrue(updatedCustomer.getRegistrationDate().equals(customer.getRegistrationDate()));
        assertTrue(updatedCustomer.getCustomerLogin().equals(customer.getCustomerLogin()));
        assertTrue(updatedCustomer.getCustomerPassword().equals(customer.getCustomerPassword()));
        assertTrue(updatedCustomer.getCustomerCardNumber().equals(customer.getCustomerCardNumber()));
        assertTrue(updatedCustomer.getCustomerCategoryId().equals(customer.getCustomerCategoryId()));
    }

    @Test
    public void delete() {
        Customer customer = new Customer("firstName3", "lastName3", "09-08-2019",
                "login03", "password03", "cardNum03", 1);
        customerDao.add(customer);
        List<Customer> customers = customerDao.findAll();
        int sizeBefore = customers.size();
        customerDao.delete(customer.getCustomerId());
        assertTrue((sizeBefore - 1) == customerDao.findAll().size());
    }
}
