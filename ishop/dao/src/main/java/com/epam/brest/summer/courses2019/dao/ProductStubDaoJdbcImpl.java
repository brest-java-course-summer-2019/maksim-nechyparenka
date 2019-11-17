package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.dao.mappers.ProductStubMapper;
import com.epam.brest.summer.courses2019.model.stub.ProductStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.epam.brest.summer.courses2019.dao.mappers.ProductStubMapper.PRODUCT_ID;

@Repository
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

    /**
     * Sql query to select all ProductStubs from DataBase
     */
    @Value("${productStub.findAll}")
    private String findAllProductStubsSql;

    /**
     * Sql query to select ProductStub by id
     */
    @Value("${productStub.findStubById}")
    private String findProductStubByIdSql;

    /**
     * Sql query to select ProductStub by category id
     */
    @Value("${productStub.findStubsByCategoryId}")
    private String findProductStubsByCategoryIdSql;

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

    @Override
    public List<ProductStub> findAllStubs() {

        LOGGER.debug("Find all ProductStubs()");

        List<ProductStub> productStubs = namedParameterJdbcTemplate.query(findAllProductStubsSql, productStubMapper);
        return productStubs;
    }

    @Override
    public Optional<ProductStub> findStubById(Integer productId) {

        LOGGER.debug("Find ProductStub by id({})", productId);

        SqlParameterSource namedParameters = new MapSqlParameterSource(PRODUCT_ID, productId);
        ProductStub productStub = namedParameterJdbcTemplate.queryForObject(findProductStubByIdSql, namedParameters,
                productStubMapper);
        return Optional.ofNullable(productStub);
    }

    @Override
    public List<ProductStub> findStubsByProductCategoryId(Integer productCategoryId) {

        LOGGER.debug("Find ProductStubs by Category id({})", productCategoryId);

        SqlParameterSource namedParameters = new MapSqlParameterSource(ProductStubMapper.PRODUCT_CATEGORY_ID,
                productCategoryId);
        List<ProductStub> results = namedParameterJdbcTemplate.query(findProductStubsByCategoryIdSql, namedParameters,
                productStubMapper);
        return results;
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
