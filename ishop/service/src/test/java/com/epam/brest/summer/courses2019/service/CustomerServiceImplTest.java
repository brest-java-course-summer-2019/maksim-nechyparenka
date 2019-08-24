package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.dao.CustomerDao;
import com.epam.brest.summer.courses2019.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

        int id = 1;
        List<Customer> customers = customerService.findByCustomerCategoryId(id);

        assertNotNull(customers);
        assertEquals("normal", customers.get(id).getCustomerCategoryId());
    }

    @Test
    public void findById() {
        int id = 1;
        Customer customer = customerService.findById(id).get();

        assertNotNull(customer);
        assertEquals("Danila", customer.getCustomerFirstName());
    }

    @Test
    public void add() {
        long count = customerService.findAll().size();
        assertThrows(DuplicateKeyException.class, () -> customerService.add(create(), create()));
        long newCount = customerService.findAll().size();
        assertEquals(count, newCount);
    }

    @Test
    public void update() {
        int id = 2;
        Customer customer = create();
        customer.setCustomerCategoryId(id);
        customerService.update(customer);
        customer = customerService.findById(id).get();

        assertNotNull(customer);
        assertEquals("name", customer.getCustomerFirstName());
    }

    @Test
    public void delete() {
        int id = 3;
        customerService.delete(id);
        assertThrows(RuntimeException.class, () -> customerService.findById(id));
    }

    private Customer create() {
        Customer customer = new Customer();
        customer.setCustomerFirstName("name");
        return customer;
    }
}
