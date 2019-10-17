package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.dao.mappers.ProductStubMapper;
import com.epam.brest.summer.courses2019.model.stub.ProductStub;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
@Transactional
@Rollback
public class ProductStubDaoJdbcImplTest {

    //private static final String PRODUCT_CATEGORY_NAME = "Cell phones & Accessories";
    private static final BigDecimal PRICE_INTERVAL_START = new BigDecimal("300");
    private static final BigDecimal PRICE_INTERVAL_END = new BigDecimal("900");
    private static final Integer PRODUCT_CATEGORY_ID = 1;
    private static final Integer PRODUCTS_QUANTITY_IN_PRICE_INTERVAL = 3;

    @Autowired
    private ProductStubDao productStubDao;

    @Test
    public void FindProductStubsFromPriceIntervalInCategory() {

        List<ProductStub> productStubs = productStubDao.findProductStubsFromPriceIntervalInCategory(PRICE_INTERVAL_START,
                PRICE_INTERVAL_END, PRODUCT_CATEGORY_ID);
        assertNotNull(productStubs);
        assertTrue(PRODUCTS_QUANTITY_IN_PRICE_INTERVAL == productStubs.size());
    }
}
