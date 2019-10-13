package com.epam.brest.summer.courses2019.dao;
import com.epam.brest.summer.courses2019.dao.mappers.ProductCategoryRowMapper;
import com.epam.brest.summer.courses2019.dao.mappers.ProductDtoRowMapper;
import com.epam.brest.summer.courses2019.dao.mappers.ProductRowMapper;
import com.epam.brest.summer.courses2019.model.Product;
import com.epam.brest.summer.courses2019.model.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class ProductDaoJdbcImpl implements ProductDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final ProductRowMapper productRowMapper;
    private final ProductDtoRowMapper productDtoRowMapper;

    /**
     * Default logger for current class
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(ProductDaoJdbcImpl.class);

    /**
     * SQL query to find all products
     */
        @Value("${product.findAll}")
    private String findAllProductsSql;

    /**
     * SQL query to find product by id
     */
    @Value("${product.findById}")
    private String findProductByIdSql;

    /**
     * SQL query to find all products by product category id
     */
    @Value("${product.findByProductCategoryId}")
    private String findByProductCategoryIdSql;

    /**
     * SQL query to add new product to DataBase
     */
    @Value("${product.add}")
    private String addProductSql;

    /**
     * SQL query to update existing product
     */
    @Value("${product.update}")
    private String updateProductSql;

    /**
     * SQL query to delete existing product
     */
    @Value("${product.delete}")
    private String deleteProductSql;

    //SQL queries for ProductDTO

    /**
     * Sql query to select all products Data Transfer Objects (DTO)
     */
    @Value("${productDTO.findAll}")
    private String findAllProductDTOsSql;

    /**
     * Sql query to select product Data Transfer Objects (DTO) by id
     */
    @Value("${productDTO.findById}")
    private String findProductDTOByIdSql;

    /**
     * Sql query to select product Data Transfer Objects (DTO) by category id
     */
    @Value("${productDTO.findProductDtoByCategoryId}")
    private String findProductDTOByCategoryIdSql;

    /**
     * Sql query to select product Data Transfer Objects (DTO) from price interval and category id
     */
    @Value("${productDTO.productDTOsFromPriceIntervalInCategory}")
    private String productDTOsFromPriceIntervalInCategorySql;

    private static final String PRODUCT_ID = "productId";
    private static final String PRODUCT_NAME = "productName";
    private static final String PRODUCT_CATEGORY_ID = "productCategoryId";
    private static final String PRODUCT_RECEIPT_DATE = "productReceiptDate";
    private static final String PRODUCT_QUANTITY = "productQuantity";
    private static final String PRODUCT_PRICE = "productPrice";

    /**
     * Begin price query parameter name
     */
    private static final String PRICE_INTERVAL_START = "priceStart";

    /**
     * End price query parameter name
     */
    private static final String PRICE_INTERVAL_END = "priceEnd";

    public ProductDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                              ProductRowMapper productRowMapper, ProductDtoRowMapper productDtoRowMapper) {

        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.productRowMapper = productRowMapper;
        this.productDtoRowMapper = productDtoRowMapper;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = namedParameterJdbcTemplate.query(findAllProductsSql, productRowMapper);
        return products;
    }

    @Override
    public List<ProductDTO> findAllProductDTOs() {
        List<ProductDTO> productDTOs = namedParameterJdbcTemplate.query(findAllProductDTOsSql,
                productDtoRowMapper);
        return productDTOs;
    }

    @Override
    public Optional<Product> findById(Integer productId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(PRODUCT_ID, productId);
        List<Product> results = namedParameterJdbcTemplate.query(findProductByIdSql, namedParameters,
                productRowMapper);
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public Optional<ProductDTO> findProductDtoById(Integer productId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(PRODUCT_ID, productId);
        List<ProductDTO> results = namedParameterJdbcTemplate.query(findProductDTOByIdSql, namedParameters,
                productDtoRowMapper);
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public List<Product> findByProductCategoryId(Integer productCategoryId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(PRODUCT_CATEGORY_ID, productCategoryId);
        List<Product> results = namedParameterJdbcTemplate.query(findByProductCategoryIdSql, namedParameters,
                productRowMapper);
        return results;
    }

    @Override
    public List<ProductDTO> findByProductDtoCategoryId(Integer productCategoryId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(PRODUCT_CATEGORY_ID, productCategoryId);
        List<ProductDTO> resultDto = namedParameterJdbcTemplate.query(findByProductCategoryIdSql, namedParameters,
                productDtoRowMapper);
        return resultDto;
    }

    /**
     * Returns all product Data Transfer Objects that fits price interval in specified category
     *
     * @param priceStart Price starting the price interval
     * @param priceEnd Price ending the price interval
     * @param productCategoryId Product category id
     * @return Product DTOs as {@code List}
     */
    @Override
    public List<ProductDTO> findProductDTOsFromPriceIntervalInCategory(BigDecimal priceStart, BigDecimal priceEnd,
                                                                       Integer productCategoryId) {

        LOGGER.debug("productDTOsFromPriceIntervalInCategory({}, {}, {})", priceStart, priceEnd, productCategoryId);

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue(PRICE_INTERVAL_START, priceStart);
        namedParameters.addValue(PRICE_INTERVAL_END, priceEnd);
        namedParameters.addValue(ProductDtoRowMapper.PRODUCT_DTO_CATEGORY_ID, productCategoryId);

        List<ProductDTO> resultDTOs = namedParameterJdbcTemplate.query(productDTOsFromPriceIntervalInCategorySql,
                namedParameters, productDtoRowMapper);

        return resultDTOs;
    }

    @Override
    public Product add(Product product) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(PRODUCT_NAME, product.getProductName());
        parameters.addValue(PRODUCT_CATEGORY_ID, product.getProductCategoryId());
        parameters.addValue(PRODUCT_RECEIPT_DATE, product.getProductReceiptDate());
        parameters.addValue(PRODUCT_QUANTITY, product.getProductQuantity());
        parameters.addValue(PRODUCT_PRICE, product.getProductPrice());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(addProductSql, parameters, generatedKeyHolder);
        product.setProductId(generatedKeyHolder.getKey().intValue());
        return product;
    }

    @Override
    public void update(Product product) {
        Optional.of(namedParameterJdbcTemplate.update(updateProductSql, new BeanPropertySqlParameterSource(product)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update Product in DataBase!"));
    }

    @Override
    public void delete(Integer productId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(PRODUCT_ID, productId);
        Optional.of(namedParameterJdbcTemplate.update(deleteProductSql, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete Product from DataBase!"));

    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }
}
