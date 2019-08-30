package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.CustomerCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
@Transactional
@Rollback
public class CustomerCategoryDaoJdbcImplTest {

    @Autowired
    CustomerCategoryDao customerCategoryDao;

    @Test
    public void add() {
        List<CustomerCategory> customerCategoryList = customerCategoryDao.findAll();
        int sizeBefore = customerCategoryList.size();

        CustomerCategory testCustomerCategory = new CustomerCategory("categoryName01");
        CustomerCategory newCustomerCategory = customerCategoryDao.add(testCustomerCategory);

        assertNotNull(newCustomerCategory.getCustomerCategoryId());
        assertTrue(newCustomerCategory.getCustomerCategoryName().equals(testCustomerCategory.getCustomerCategoryName()));
        assertTrue((sizeBefore + 1) == customerCategoryDao.findAll().size());
    }

    @Test
    public void findAll() {
        List<CustomerCategory> customersCategories = customerCategoryDao.findAll();
        Assert.assertNotNull(customersCategories);
        Assert.assertTrue(customersCategories.size() > 0);
    }

    @Test
    public void update() {
        CustomerCategory customerCategory = customerCategoryDao.findCustomerCategoryById(2).get();
        customerCategory.setCustomerCategoryName("newCategoryName");

        customerCategoryDao.update(customerCategory);
        CustomerCategory updatedCustomerCategory = customerCategoryDao.findCustomerCategoryById(customerCategory
                .getCustomerCategoryId()).get();
        assertTrue(updatedCustomerCategory.getCustomerCategoryId().equals(customerCategory.getCustomerCategoryId()));
    }

    @Test
    public void deleteCategory() {
        CustomerCategory customerCategory = new CustomerCategory("categoryNameNew");
        customerCategoryDao.add(customerCategory);
        List<CustomerCategory> customersCategories = customerCategoryDao.findAll();
        int sizeBefore = customersCategories.size();
        customerCategoryDao.delete(customerCategory.getCustomerCategoryId());
        assertTrue((sizeBefore - 1) == customerCategoryDao.findAll().size());
    }
}
