package com.epam.brest.summer.courses2019.dao;
import com.epam.brest.summer.courses2019.model.Product;
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

    private static final String SELECT_ALL =
            "SELECT product_id, product_name, product_category_id, product_receiptdate, "
                    + "product_quantity, product_price FROM product ORDER BY 2";

    private static final String FIND_BY_ID =
            "SELECT product_id, product_name, product_category_id, product_receiptdate, "
                    + "product_quantity, product_price FROM product WHERE product_id = :productId";

    private static final String FIND_BY_PRODUCT_CATEGORY_ID =
            "SELECT product_id, product_name, product_category_id, product_receiptdate, "
                    + "product_quantity, product_price FROM product WHERE product_category_id = :productCategoryId";

    private static final String ADD_PRODUCT =
            "INSERT INTO product (product_name, product_category_id, product_receiptdate, "
                    + "product_quantity, product_price) VALUES (:productName, :productCategoryId, :productReceiptDate, "
                    + ":productQuantity, :productPrice)";

    private static final String UPDATE =
            "UPDATE product SET product_name = :productName, product_category_id = :productCategoryId, "
                    + "product_receiptdate = :productReceiptDate, product_quantity = :productQuantity, "
                    + "product_price = :productPrice WHERE product_id = :productId";

    private static final String DELETE =
            "DELETE FROM product WHERE product_id = :productId";

//    private static final String FIND_BALANCE_BY_ID =
//            "SELECT product_id, product_name, product_quantity, product_price, product_quantity * product_price FROM product "
//                    + "WHERE product_id = :productId";

    private static final String PRODUCT_ID = "productId";
    private static final String PRODUCT_NAME = "productName";
    private static final String PRODUCT_CATEGORY_ID = "productCategoryId";
    private static final String PRODUCT_RECEIPT_DATE = "productReceiptDate";
    private static final String PRODUCT_QUANTITY = "productQuantity";
    private static final String PRODUCT_PRICE = "productPrice";

    public ProductDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = namedParameterJdbcTemplate.query(SELECT_ALL, new ProductRowMapper());
        return products;
    }

    @Override
    public Optional<Product> findById(Integer productId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(PRODUCT_ID, productId);
        List<Product> results = namedParameterJdbcTemplate.query(FIND_BY_ID, namedParameters,
                new ProductRowMapper());
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public List<Product> findByProductCategoryId(Integer productCategoryId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(PRODUCT_CATEGORY_ID, productCategoryId);
        List<Product> results = namedParameterJdbcTemplate.query(FIND_BY_PRODUCT_CATEGORY_ID, namedParameters,
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
        namedParameterJdbcTemplate.update(ADD_PRODUCT, parameters, generatedKeyHolder);
        product.setProductId(generatedKeyHolder.getKey().intValue());
        return product;
    }

    @Override
    public void update(Product product) {
        Optional.of(namedParameterJdbcTemplate.update(UPDATE, new BeanPropertySqlParameterSource(product)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update Product in DataBase!"));
    }

    @Override
    public void delete(Integer productId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(PRODUCT_ID, productId);
        Optional.of(namedParameterJdbcTemplate.update(DELETE, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete Product from DataBase!"));

    }

    @Override
    public BigDecimal findBalanceById(Integer productId) {
        List<Product> products = namedParameterJdbcTemplate.query(SELECT_ALL, new ProductRowMapper());
        BigDecimal balance = new BigDecimal(String.valueOf(products.get(productId).getProductQuantity()));
        return balance;
    }

    private class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet resultSet, int i) throws SQLException {

            Product product = new Product();

            product.setProductId(resultSet.getInt("product_id"));
            product.setProductName(resultSet.getString("product_name"));
            product.setProductCategoryId(resultSet.getInt("product_category_id"));
            product.setProductReceiptDate(resultSet.getDate("product_receiptdate").toLocalDate());
            product.setProductQuantity(resultSet.getBigDecimal("product_quantity"));
            product.setProductPrice(resultSet.getBigDecimal("product_price"));

            return product;
        }
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }
}
