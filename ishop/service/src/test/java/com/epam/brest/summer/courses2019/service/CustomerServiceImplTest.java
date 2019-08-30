package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml"})
@Transactional
@Rollback
public class CustomerServiceImplTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void findAll() {
        List<Customer> customers = customerService.findAll();

        assertNotNull(customers);
        assertFalse(customers.isEmpty());
    }

    @Test
    public void findByCustomerCategoryId() {

        Integer categoryId = 1;
        List<Customer> customers = customerService.findByCustomerCategoryId(categoryId);
        Customer foundCustomer = customers.get(1);

        assertNotNull(customers);
        assertEquals(categoryId, foundCustomer.getCustomerCategoryId());
    }

    @Test
    public void findById() {
        int id = 1;
        Customer customer = customerService.findById(id);

        assertNotNull(customer);
        assertEquals("Danila", customer.getCustomerFirstName());
    }

    @Test
    public void add() {
        long count = customerService.findAll().size();
        customerService.add(create());
        long newCount = customerService.findAll().size();
        assertNotEquals(count, newCount);
    }

    @Test
    public void update() {
        Customer customer = create();
        customerService.add(customer);
        customer.setCustomerFirstName("name");
        customerService.update(customer);
        Customer updatedCustomer = customerService.findById(customer.getCustomerId());

        assertNotNull(updatedCustomer);
        assertEquals("name", updatedCustomer.getCustomerFirstName());
    }

    @Test
    public void delete() {
        int id = 3;
        customerService.delete(id);
        assertThrows(RuntimeException.class, () -> customerService.findById(id));
    }

    private Customer create() {
        Customer customer = new Customer("newFirstName", "newLastName",
                LocalDate.of(2019,8,8), "newLogin", "newPassword",
                "newCardNumber", 2);
        return customer;
    }
}
