package com.epam.brest.summer.courses2019.dao;
import com.epam.brest.summer.courses2019.dao.mappers.ProductMapper;
import com.epam.brest.summer.courses2019.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductDaoJdbcImpl implements ProductDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final ProductMapper productMapper;

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

    private static final String PRODUCT_ID = "productId";
    private static final String PRODUCT_NAME = "productName";
    private static final String PRODUCT_CATEGORY_ID = "productCategoryId";
    private static final String PRODUCT_SUPPLIER_NAME = "productSupplierName";
    private static final String PRODUCT_RECEIPT_DATE = "productReceiptDate";
    private static final String PRODUCT_QUANTITY = "productQuantity";
    private static final String PRODUCT_PRICE = "productPrice";

    public ProductDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                              ProductMapper productMapper) {

        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> findAll() {

        LOGGER.debug("Find all Products()");

        List<Product> products = namedParameterJdbcTemplate.query(findAllProductsSql, productMapper);
        return products;
    }

    @Override
    public Optional<Product> findById(Integer productId) {

        LOGGER.debug("Find Product by id({})", productId);

        SqlParameterSource namedParameters = new MapSqlParameterSource(ProductMapper.PRODUCT_ID, productId);
        Product product = namedParameterJdbcTemplate.queryForObject(findProductByIdSql, namedParameters,
                productMapper);
        return Optional.ofNullable(product);
    }

    @Override
    public List<Product> findByProductCategoryId(Integer productCategoryId) {

        LOGGER.debug("Find Product by Category id({})", productCategoryId);

        SqlParameterSource namedParameters = new MapSqlParameterSource(PRODUCT_CATEGORY_ID, productCategoryId);
        List<Product> results = namedParameterJdbcTemplate.query(findByProductCategoryIdSql, namedParameters,
                productMapper);
        return results;
    }

    @Override
    public Product add(Product product) {

        LOGGER.debug("Add Product({})", product);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(PRODUCT_NAME, product.getProductName());
        parameters.addValue(PRODUCT_CATEGORY_ID, product.getProductCategoryId());
        parameters.addValue(PRODUCT_SUPPLIER_NAME, product.getProductSupplierName());
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

        LOGGER.debug("Update Product({})", product);

        Optional.of(namedParameterJdbcTemplate.update(updateProductSql, new BeanPropertySqlParameterSource(product)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update Product in DataBase!"));
    }

    @Override
    public void delete(Integer productId) {

        LOGGER.debug("Delete Product({})", productId);

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
