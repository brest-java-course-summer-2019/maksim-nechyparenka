package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.dao.mappers.ProductStubMapper;
import com.epam.brest.summer.courses2019.model.stub.ProductStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ProductStubDaoJdbcImpl implements ProductStubDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final ProductStubMapper productStubMapper;

    /**
     * Default logger for current class
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(ProductDaoJdbcImpl.class);

    /**
     * Begin price query parameter name
     */
    private static final String PRICE_INTERVAL_START = "priceStart";

    /**
     * End price query parameter name
     */
    private static final String PRICE_INTERVAL_END = "priceEnd";

    //private static final String PRODUCT_CATEGORY_ID = "productCategoryId";

    /**
     * Sql query to select products from price interval in category
     */
    @Value("${productStub.productStubsFromPriceIntervalInCategory}")
    private String productStubsFromPriceIntervalInCategorySql;

    public ProductStubDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                  ProductStubMapper productStubMapper) {

        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.productStubMapper = productStubMapper;
    }

    /**
     * Returns all products that fits price interval in specified category
     *
     * @param priceStart Price starting the price interval
     * @param priceEnd Price ending the price interval
     * @param productCategoryId Product category id
     * @return ProductStubs as {@code List}
     */
    @Override
    public List<ProductStub> findProductStubsFromPriceIntervalInCategory(BigDecimal priceStart, BigDecimal priceEnd,
                                                                           Integer productCategoryId) {

        LOGGER.debug("productStubsFromPriceIntervalInCategory({}, {}, {})", priceStart, priceEnd, productCategoryId);

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue(PRICE_INTERVAL_START, priceStart);
        namedParameters.addValue(PRICE_INTERVAL_END, priceEnd);
        namedParameters.addValue(ProductStubMapper.PRODUCT_CATEGORY_ID, productCategoryId);

        List<ProductStub> resultStubs = namedParameterJdbcTemplate.query(productStubsFromPriceIntervalInCategorySql,
                namedParameters, productStubMapper);

        return resultStubs;
    }
}
