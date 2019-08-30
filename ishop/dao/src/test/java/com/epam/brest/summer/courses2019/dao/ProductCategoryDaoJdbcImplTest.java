package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.ProductCategory;
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
public class ProductCategoryDaoJdbcImplTest {

    @Autowired
    ProductCategoryDao productCategoryDao;

    @Test
    public void add() {
        List<ProductCategory> productCategoryList = productCategoryDao.findAll();
        int sizeBefore = productCategoryList.size();

        ProductCategory testProductCategory = new ProductCategory("productCategory01");
        ProductCategory newProductCategory = productCategoryDao.add(testProductCategory);

        assertNotNull(newProductCategory.getProductCategoryId());
        assertTrue(newProductCategory.getProductCategoryName().equals(testProductCategory.getProductCategoryName()));
        assertTrue((sizeBefore + 1) == productCategoryDao.findAll().size());
    }

    @Test
    public void findAll() {
        List<ProductCategory> customersCategories = productCategoryDao.findAll();
        Assert.assertNotNull(customersCategories);
        Assert.assertTrue(customersCategories.size() > 0);
    }

    @Test
    public void update() {
        ProductCategory productCategory = productCategoryDao.findProductCategoryById(2).get();
        productCategory.setProductCategoryName("newCategoryName");

        productCategoryDao.update(productCategory);
        ProductCategory updatedProductCategory = productCategoryDao.findProductCategoryById(productCategory
                .getProductCategoryId()).get();
        assertTrue(updatedProductCategory.getProductCategoryId().equals(productCategory.getProductCategoryId()));
    }

    @Test
    public void deleteCategory() {
        ProductCategory productCategory = new ProductCategory("productCategoryNew");
        productCategoryDao.add(productCategory);
        List<ProductCategory> productCategories = productCategoryDao.findAll();
        int sizeBefore = productCategories.size();
        productCategoryDao.delete(productCategory.getProductCategoryId());
        assertTrue((sizeBefore - 1) == productCategoryDao.findAll().size());
    }
}
