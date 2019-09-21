package com.epam.brest.summer.courses2019.dao.mapper;

import com.epam.brest.summer.courses2019.model.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Product Data Transfer Object Mapper
 *
 * @see RowMapper
 * @see Product
 * @author Maksim Nechyparenka
 */

@Component
public class ProductRowMapper implements RowMapper<Product> {

    /**
     * Product id query parameter name
     */
    public static final String PRODUCT_ID = "product_id";

    /**
     * Product name query parameter name
     */
    public static final String PRODUCT_NAME = "product_name";

    /**
     * Product category id query parameter name
     */
    public static final String PRODUCT_CATEGORY_ID = "product_category_id";

    /**
     * Product receipt date query parameter name
     */
    public static final String PRODUCT_RECEIPTDATE = "product_receiptdate";

    /**
     * Product quantity query parameter name
     */
    public static final String PRODUCT_QUANTITY = "product_quantity";

    /**
     * Product price query parameter name
     */
    public static final String PRODUCT_PRICE = "product_price";

    /**
     * Map values from sql result set to product object method
     *
     * @param resultSet sql result set with necessary values
     * @param i number of rows in result set
     * @return New Product object
     * @throws SQLException If can't extract values from result set
     */

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {

        Product product = new Product();

        product.setProductId(resultSet.getInt(PRODUCT_ID));
        product.setProductName(resultSet.getString(PRODUCT_NAME));
        product.setProductCategoryId(resultSet.getInt(PRODUCT_CATEGORY_ID));
        product.setProductReceiptDate(resultSet.getDate(PRODUCT_RECEIPTDATE).toLocalDate());
        product.setProductQuantity(resultSet.getBigDecimal(PRODUCT_QUANTITY));
        product.setProductPrice(resultSet.getBigDecimal(PRODUCT_PRICE));

        return product;
    }
}