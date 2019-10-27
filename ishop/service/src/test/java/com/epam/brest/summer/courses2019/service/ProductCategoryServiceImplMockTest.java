package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.dao.ProductCategoryDao;
import com.epam.brest.summer.courses2019.model.ProductCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class ProductCategoryServiceImplMockTest {

    private static final String TEST_FIRST_PRODUCT_CATEGORY_NAME = "firstCategoryName";
    private static final String TEST_SECOND_PRODUCT_CATEGORY_NAME = "secondCategoryName";
    private static final Integer TEST_FIRST_PRODUCT_CATEGORY_ID = 4;
    private static final Integer TEST_SECOND_PRODUCT_CATEGORY_ID = 5;
    private static final Integer PRODUCT_CATEGORY_QUANTITY = 2;
    private static final Integer INVOCATIONS = 1;
    private static ProductCategory TEST_FIRST_PRODUCT_CATEGORY;
    private static ProductCategory TEST_SECOND_PRODUCT_CATEGORY;

    @Mock
    private ProductCategoryDao productCategoryDao;

    @Captor
    private ArgumentCaptor<ProductCategory> categoryCaptor;

    @InjectMocks
    private ProductCategoryServiceImpl productCategoryService;

    @BeforeAll
    static void initBlock() {

        TEST_FIRST_PRODUCT_CATEGORY = createCategory(TEST_FIRST_PRODUCT_CATEGORY_ID, TEST_FIRST_PRODUCT_CATEGORY_NAME);
        TEST_SECOND_PRODUCT_CATEGORY = createCategory(TEST_SECOND_PRODUCT_CATEGORY_ID, TEST_SECOND_PRODUCT_CATEGORY_NAME);
    }

    @Test
    void findAllCategories() {

        Mockito.when(productCategoryDao.findAll()).thenReturn(Arrays.asList(TEST_FIRST_PRODUCT_CATEGORY,
                TEST_SECOND_PRODUCT_CATEGORY));

        List<ProductCategory> result = productCategoryService.findAll();

        assertNotNull(result);
        assertTrue(PRODUCT_CATEGORY_QUANTITY == result.size());

        Mockito.verify(productCategoryDao, Mockito.times(INVOCATIONS)).findAll();
        Mockito.verifyNoMoreInteractions(productCategoryDao);
    }

    @Test
    void findCategoryById() {

        Mockito.when(productCategoryDao.findProductCategoryById(any()))
                .thenReturn(Optional.of(createCategory(TEST_FIRST_PRODUCT_CATEGORY_ID, TEST_FIRST_PRODUCT_CATEGORY_NAME)));

        ProductCategory productCategory = productCategoryService
                .findProductCategoryById(TEST_FIRST_PRODUCT_CATEGORY_ID);

        assertNotNull(productCategory);
        assertEquals(TEST_FIRST_PRODUCT_CATEGORY_ID, productCategory.getProductCategoryId());

        Mockito.verify(productCategoryDao, Mockito.times(INVOCATIONS)).findProductCategoryById(anyInt());
    }

    @Test
    void addCategory() {

        Mockito.when(productCategoryDao.add(any())).thenReturn(createCategory(TEST_FIRST_PRODUCT_CATEGORY_ID,
                TEST_FIRST_PRODUCT_CATEGORY_NAME));

        productCategoryService.add(TEST_FIRST_PRODUCT_CATEGORY);

        Mockito.verify(productCategoryDao, Mockito.times(INVOCATIONS)).add(any());
    }

    @Test
    void updateCategory() {

        productCategoryService.update(any(ProductCategory.class));
        Mockito.verify(productCategoryDao, Mockito.times(INVOCATIONS)).update(any());
    }

    @Test
    void deleteCategory() {

        productCategoryService.delete(anyInt());
        Mockito.verify(productCategoryDao, Mockito.times(INVOCATIONS)).delete(anyInt());
    }

    private static ProductCategory createCategory(Integer categoryId, String categoryName) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(categoryId);
        productCategory.setProductCategoryName(categoryName);
        return productCategory;
    }
}
