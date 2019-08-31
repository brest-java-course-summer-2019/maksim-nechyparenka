package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.model.CustomerCategory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml"})
@Transactional
@Rollback
public class CustomerCategoryServiceImplTest {

    @Autowired
    private CustomerCategoryService customerCategoryService;

    @Test
    public void findAll() {
        List<CustomerCategory> customerCategoryList = customerCategoryService.findAll();

        assertNotNull(customerCategoryList);
        assertFalse(customerCategoryList.isEmpty());
    }

    @Test
    public void findCustomerCategoryById() {

        Integer categoryId = 1;
        Optional<CustomerCategory> customerCategories = customerCategoryService.findCustomerCategoryById(categoryId);
        assertNotNull(customerCategories);
        CustomerCategory foundCategory = customerCategories.get();

        assertNotNull(customerCategories);
        assertEquals(categoryId, foundCategory.getCustomerCategoryId());
    }

    @Test
    public void updateCategory() {
        assertNotNull(customerCategoryService);
        int id = 1;
        CustomerCategory customerCategory = customerCategoryService.findCustomerCategoryById(id).get();
        assertNotNull(customerCategory);
        customerCategory.setCustomerCategoryName("newCategoryName");

        customerCategoryService.update(customerCategory);
        CustomerCategory updatedCustomerCategory = customerCategoryService
                .findCustomerCategoryById(customerCategory.getCustomerCategoryId()).get();
        assertNotNull(updatedCustomerCategory);
        assertEquals(customerCategory.getCustomerCategoryName(), updatedCustomerCategory.getCustomerCategoryName());
    }

    @Test
    public void deleteCategory() {
        CustomerCategory customerCategory = new CustomerCategory("categoryName");
        customerCategoryService.add(customerCategory);
        List<CustomerCategory> customerCategories = customerCategoryService.findAll();
        int sizeBefore = customerCategories.size();
        customerCategoryService.delete(customerCategory.getCustomerCategoryId());
        assertTrue((sizeBefore - 1) == customerCategoryService.findAll().size());
    }

}
