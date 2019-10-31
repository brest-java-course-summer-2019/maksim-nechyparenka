package com.epam.brest.summer.courses2019.dao.mappers;

import com.epam.brest.summer.courses2019.model.stub.ProductStub;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ProductStub Object Mapper
 *
 * @see RowMapper
 * @see ProductStub
 * @author Maksim Nechyparenka
 */

@Component
public class ProductStubMapper implements RowMapper<ProductStub> {

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

//    /**
//     * Product category name query parameter name
//     */
//    public static final String PRODUCT_CATEGORY_NAME = "product_category_name";

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
    public ProductStub mapRow(ResultSet resultSet, int i) throws SQLException {

        ProductStub productStub = new ProductStub();

        productStub.setProductId(resultSet.getInt(PRODUCT_ID));
        productStub.setProductName(resultSet.getString(PRODUCT_NAME));
        productStub.setProductCategoryId(resultSet.getInt(PRODUCT_CATEGORY_ID));
//        productStub.setProductCategoryName(resultSet.getString(PRODUCT_CATEGORY_NAME));
        productStub.setProductPrice(resultSet.getBigDecimal(PRODUCT_PRICE));

        return productStub;
    }
}