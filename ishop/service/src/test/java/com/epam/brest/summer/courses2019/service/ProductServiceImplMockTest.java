package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.dao.ProductDao;
import com.epam.brest.summer.courses2019.dao.ProductStubDao;
import com.epam.brest.summer.courses2019.model.Product;
import com.epam.brest.summer.courses2019.model.stub.ProductStub;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplMockTest {

    private static final Integer TEST_PRODUCT_ID = 1;
    private static final String TEST_PRODUCT_NAME = "productName";
    private static final Integer TEST_PRODUCT_CATEGORY_ID = 1;
    private static final BigDecimal TEST_PRODUCT_INTERVAL_START_PRICE = new BigDecimal("300");
    private static final BigDecimal TEST_PRODUCT_INTERVAL_END_PRICE = new BigDecimal("900");
    private static final BigDecimal TEST_PRODUCT_PRICE_FIRST = new BigDecimal("555");
    private static final BigDecimal TEST_PRODUCT_PRICE_SECOND = new BigDecimal("888");
    private static final Integer PRODUCT_QUANTITY = 2;
    private static final Integer INVOCATIONS = 1;
    private static ProductStub TEST_FIRST_PRODUCT_STUB;
    private static ProductStub TEST_SECOND_PRODUCT_STUB;
    private static Product TEST_FIRST_PRODUCT;
    private static Product TEST_SECOND_PRODUCT;

    @Mock
    private ProductDao productDao;

    @Mock
    private ProductStubDao productStubDao;

    @Captor
    private ArgumentCaptor<Product> productCaptor;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeAll
    static void initBlock() {

        TEST_FIRST_PRODUCT_STUB = createStub(TEST_PRODUCT_CATEGORY_ID, TEST_PRODUCT_PRICE_FIRST);
        TEST_SECOND_PRODUCT_STUB = createStub(TEST_PRODUCT_CATEGORY_ID, TEST_PRODUCT_PRICE_SECOND);
        TEST_FIRST_PRODUCT = createProduct();
        TEST_SECOND_PRODUCT = createProduct();
    }

    @Test
    public void findAll() {

        Mockito.when(productDao.findAll()).thenReturn(Arrays.asList(TEST_FIRST_PRODUCT, TEST_SECOND_PRODUCT));

        List<Product> result = productService.findAll();

        assertNotNull(result);
        assertTrue(PRODUCT_QUANTITY == result.size());

        Mockito.verify(productDao, Mockito.times(INVOCATIONS)).findAll();
        Mockito.verifyNoMoreInteractions(productDao);
    }

    @Test
    public void findAllStubs() {

        Mockito.when(productStubDao.findAllStubs()).thenReturn(Arrays.asList(TEST_FIRST_PRODUCT_STUB,
                TEST_SECOND_PRODUCT_STUB));

        List<ProductStub> result = productService.findAllStubs();

        assertNotNull(result);
        assertTrue(PRODUCT_QUANTITY == result.size());

        Mockito.verify(productStubDao, Mockito.times(INVOCATIONS)).findAllStubs();
        Mockito.verifyNoMoreInteractions(productStubDao);
    }

    @Test
    public void findById() {

        Mockito.when(productDao.findById(TEST_PRODUCT_ID)).thenReturn(Optional.of(createProduct()));

        Product product = productService.findById(TEST_PRODUCT_ID);

        assertNotNull(product);
        assertEquals(TEST_PRODUCT_NAME, product.getProductName());

        Mockito.verify(productDao).findById(TEST_PRODUCT_ID);
    }

    @Test
    public void findStubById() {

        Mockito.when(productStubDao.findStubById(TEST_PRODUCT_ID)).thenReturn(Optional
                .of(createStub(TEST_PRODUCT_ID, TEST_PRODUCT_PRICE_FIRST)));

        ProductStub productStub = productService.findStubById(TEST_PRODUCT_ID);

        assertNotNull(productStub);
        assertEquals(TEST_PRODUCT_NAME, productStub.getProductName());

        Mockito.verify(productStubDao).findStubById(TEST_PRODUCT_ID);
    }

    @Test
    public void findProductStubsFromPriceIntervalInCategory() {

        Mockito.when(productStubDao.findProductStubsFromPriceIntervalInCategory(any(), any(), any()))
                .thenReturn(Arrays.asList(TEST_FIRST_PRODUCT_STUB, TEST_SECOND_PRODUCT_STUB));

        List<ProductStub> productStubs = productService
                .findProductStubsFromPriceIntervalInCategory(TEST_PRODUCT_INTERVAL_START_PRICE,
                        TEST_PRODUCT_INTERVAL_END_PRICE, TEST_PRODUCT_CATEGORY_ID);

        assertNotNull(productStubs);
        assertTrue(PRODUCT_QUANTITY == productStubs.size());

        Mockito.verify(productStubDao, Mockito.times(INVOCATIONS))
                .findProductStubsFromPriceIntervalInCategory(any(), any(), any());
        Mockito.verifyNoMoreInteractions(productStubDao);
    }

    @Test
    public void update() {

        productService.update(createProduct());

        Mockito.verify(productDao).update(productCaptor.capture());

        Product product = productCaptor.getValue();
        assertNotNull(product);
        assertEquals(TEST_PRODUCT_NAME, product.getProductName());
    }

    @Test
    public void delete() {

        productService.delete(TEST_PRODUCT_ID);

        Mockito.verify(productDao).delete(TEST_PRODUCT_ID);
    }

    private static Product createProduct() {
        Product product = new Product();
        product.setProductName(TEST_PRODUCT_NAME);
        return product;
    }

    private static ProductStub createStub(Integer categoryId, BigDecimal price) {
        ProductStub productStub = new ProductStub();
        productStub.setProductName(TEST_PRODUCT_NAME);
        productStub.setProductCategoryId(categoryId);
        productStub.setProductPrice(price);
        return productStub;
    }
}
