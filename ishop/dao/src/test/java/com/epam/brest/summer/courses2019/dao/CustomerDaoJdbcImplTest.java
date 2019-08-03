package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
        assertNotNull(customers);
        assertTrue(customers.size() > 0);
    }

    @Test
    public void findByCustomerCategoryId() {
        List<Customer> customers = customerDao.findByCustomerCategoryId(1);
        assertNotNull(customerDao);
        assertTrue(customers.size() > 0);
    }

    @Test
    public void findById() {
        assertNotNull(customerDao);
        Customer customer = customerDao.findById(1).get();
        assertTrue(customer.getCustomerId().equals(1));
        assertTrue(customer.getCustomerFirstName().equals("Danila"));
        assertTrue(customer.getCustomerLastName().equals("Kozlovsky"));
        assertTrue(customer.getRegistrationDate().equals("08-08-2018"));
        assertTrue(customer.getCustomerLogin().equals("login01"));
        assertTrue(customer.getCustomerPassword().equals("password01"));
        assertTrue(customer.getCustomerCardNumber().equals("1234 5678 9012 3456"));
        assertTrue(customer.getCustomerCategoryId().equals("normal"));
    }

    @Test
    public void add() {
        List<Customer> customers = customerDao.findAll();
        int sizeBefore = customers.size();
        Customer customer = new Customer("email2", "firstName2", "lastName2", new BigDecimal("302"), 1);
        Employee newEmployee = employeeDao.add(employee);
        assertNotNull(newEmployee.getEmployeeId());
        assertTrue(newEmployee.getFirstName().equals(employee.getFirstName()));
        assertTrue(newEmployee.getLastName().equals(employee.getLastName()));
        assertTrue(newEmployee.getEmail().equals(employee.getEmail()));
        assertTrue(newEmployee.getSalary().equals(employee.getSalary()));
        assertTrue(newEmployee.getDepartmentId().equals(employee.getDepartmentId()));
        assertTrue((sizeBefore + 1) == employeeDao.findAll().size());
    }


}
