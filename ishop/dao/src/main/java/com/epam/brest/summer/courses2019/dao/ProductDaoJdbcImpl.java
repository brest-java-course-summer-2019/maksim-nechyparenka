package com.epam.brest.summer.courses2019.dao;
import com.epam.brest.summer.courses2019.dao.mapper.ProductDtoRowMapper;
import com.epam.brest.summer.courses2019.dao.mapper.ProductRowMapper;
import com.epam.brest.summer.courses2019.model.Product;
import com.epam.brest.summer.courses2019.model.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class ProductDaoJdbcImpl implements ProductDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final ProductRowMapper productRowMapper;
    private final ProductDtoRowMapper productDtoRowMapper;

    /**
     * SQL statement to find all products
     */
        @Value("${product.findAll}")
    private String findAllProductsSql;

    /**
     * SQL statement to find product by id
     */
    @Value("${product.findById}")
    private String findProductByIdSql;

    /**
     * SQL statement to find all products by product category id
     */
    @Value("${product.findByProductCategoryId}")
    private String findByProductCategoryIdSql;

    /**
     * SQL statement to add new product to DB
     */
    @Value("${product.add}")
    private String addProductSql;

    /**
     * SQL statement to update existing product
     */
    @Value("${product.update}")
    private String updateProductSql;

    /**
     * SQL statement to delete existing product
     */
    @Value("${product.delete}")
    private String deleteProductSql;

    //SQL statements for ProductDTO

    /**
     * Sql statement to select all products Data Transfer Objects (DTO)
     */
    @Value("${productDTO.findAll}")
    private String findAllProductDTOsSql;

    /**
     * Sql statement to select product Data Transfer Objects (DTO) by id
     */
    @Value("${productDTO.findById}")
    private String findProductDTOByIdSql;




    private static final String PRODUCT_ID = "productId";
    private static final String PRODUCT_NAME = "productName";
    private static final String PRODUCT_CATEGORY_ID = "productCategoryId";
    private static final String PRODUCT_RECEIPT_DATE = "productReceiptDate";
    private static final String PRODUCT_QUANTITY = "productQuantity";
    private static final String PRODUCT_PRICE = "productPrice";

    @Autowired
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
                new ProductRowMapper());
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public List<Product> findByProductCategoryId(Integer productCategoryId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(PRODUCT_CATEGORY_ID, productCategoryId);
        List<Product> results = namedParameterJdbcTemplate.query(findByProductCategoryIdSql, namedParameters,
                new ProductRowMapper());
        return results;
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

    @Override
    public BigDecimal findBalanceById(Integer productId) {
        List<Product> products = namedParameterJdbcTemplate.query(findAllProductsSql, productRowMapper);
        BigDecimal balance = new BigDecimal(String.valueOf(products.get(productId).getProductQuantity()));
        return balance;
    }

//    private class ProductRowMapper implements RowMapper<Product> {
//        @Override
//        public Product mapRow(ResultSet resultSet, int i) throws SQLException {
//
//            Product product = new Product();
//
//            product.setProductId(resultSet.getInt("product_id"));
//            product.setProductName(resultSet.getString("product_name"));
//            product.setProductCategoryId(resultSet.getInt("product_category_id"));
//            product.setProductReceiptDate(resultSet.getDate("product_receiptdate").toLocalDate());
//            product.setProductQuantity(resultSet.getBigDecimal("product_quantity"));
//            product.setProductPrice(resultSet.getBigDecimal("product_price"));
//
//            return product;
//        }
//    }

//    private class ProductDTORowMapper implements RowMapper<ProductDTO> {
//        @Override
//        public ProductDTO mapRow(ResultSet resultSet, int i) throws SQLException {
//
//            ProductDTO productDTO = new ProductDTO();
//
//            productDTO.setProductId(resultSet.getInt("product_id"));
//            productDTO.setProductName(resultSet.getString("product_name"));
//            productDTO.setProductCategoryId(resultSet.getInt("product_category_id"));
//            productDTO.setProductReceiptDate(resultSet.getDate("product_receiptdate").toLocalDate());
//            productDTO.setProductQuantity(resultSet.getBigDecimal("product_quantity"));
//            productDTO.setProductPrice(resultSet.getBigDecimal("product_price"));
//
//            return productDTO;
//        }
//    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }
}
