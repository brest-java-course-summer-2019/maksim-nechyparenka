package com.epam.brest.summer.courses2019.dao;
import com.epam.brest.summer.courses2019.model.Customer;
import com.epam.brest.summer.courses2019.model.Product;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    private final static String SELECT_ALL =
            "SELECT p.product_id, p.product_name, p.product_category_id, p.product_receiptdate, "
                    + "p.product_quantity, p.product_price FROM product p ORDER BY 2";

    private static final String FIND_BY_ID =
            "SELECT product_id, product_name, product_category_id, product_receiptdate, "
                    + "product_quantity, product_price FROM product WHERE product_id = :productId";

    private static final String FIND_BY_PRODUCT_CATEGORY_ID =
            "SELECT product_id, product_name, product_category_id, product_receiptdate, "
                    + "product_quantity, product_price FROM product WHERE product_category = :productCategory";

    private final static String ADD_PRODUCT =
            "INSERT INTO product (product_name, product_category_id, product_receiptdate, "
                    + "product_quantity, product_price) VALUES (:productName, :productCategoryId, :productReceiptDate, "
                    + ":productQuantity, :productPrice)";

    private static final String UPDATE =
            "UPDATE product SET product_name = :productName, product_category_id = :productCategoryId, "
                    + "product_receiptdate = :productReceiptDate, product_quantity = :productQuantity, "
                    + "product_price = :productPrice WHERE product_id = :productId";

    private static final String DELETE =
            "DELETE FROM product WHERE product_id = :productId";

    private static final String PRODUCT_ID = "productId";
    private static final String PRODUCT_CATEGORY_ID = "productCategoryId";

    public ProductDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Product add(Product product) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("productName", product.getProductName());
        parameters.addValue("productCategoryId", product.getProductCategoryId());
        parameters.addValue("productReceiptDate", product.getReceiptDate());
        parameters.addValue("productQuantity", product.getProductQuantity());
        parameters.addValue("productPrice", product.getProductPrice());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_PRODUCT, parameters, generatedKeyHolder);
        product.setProductId(generatedKeyHolder.getKey().intValue());
        return product;
    }

    @Override
    public void update(Product product) {
        Optional.of(namedParameterJdbcTemplate.update(UPDATE, new BeanPropertySqlParameterSource(product)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update product in DataBase!"));
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    @Override
    public void delete(Integer productId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(PRODUCT_ID, productId);
        Optional.of(namedParameterJdbcTemplate.update(DELETE, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete product from DataBase!"));

    }

    @Override
    public List<Product> findAll() {
        List<Product> products = namedParameterJdbcTemplate.query(SELECT_ALL, new ProductRowMapper());
        return products;
    }

    @Override
    public Product findById(Integer productId) {
        List<Product> products = namedParameterJdbcTemplate.query(SELECT_ALL, new ProductRowMapper());
        return products.get(productId);
    }
    @Override
    public BigDecimal findBalanceById(Integer productId) {
        List<Product> products = namedParameterJdbcTemplate.query(SELECT_ALL, new ProductRowMapper());
        BigDecimal balance = new BigDecimal(String.valueOf(products.get(productId).getProductQuantity()));
        return balance;
    }

    @Override
    public Optional<Product> findByProductCategoryId(Integer productCategoryId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(PRODUCT_CATEGORY_ID, productCategoryId);
        List<Product> results = namedParameterJdbcTemplate.query(FIND_BY_PRODUCT_CATEGORY_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(Product.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    private class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet resultSet, int i) throws SQLException {
            Product product = new Product();
            product.setProductId(resultSet.getInt("product_id"));
            product.setProductName(resultSet.getString("product_name"));
            product.setProductCategoryId(resultSet.getInt("product_category_id"));
            product.setReceiptDate(resultSet.getString("product_receiptdate"));
            product.setProductQuantity(resultSet.getBigDecimal("product_quantity"));
            product.setProductPrice(resultSet.getBigDecimal("product_price"));

            return product;
        }
    }
}
