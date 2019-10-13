package com.epam.brest.summer.courses2019.dao.mappers;

import com.epam.brest.summer.courses2019.model.ProductCategory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ProductCategory Object Mapper
 *
 * @see RowMapper
 * @see com.epam.brest.summer.courses2019.model.ProductCategory
 * @author Maksim Nechyparenka
 */

@Component
public class ProductCategoryRowMapper implements RowMapper<ProductCategory> {

    /**
     * Product category id query parameter name
     */
    public static final String PRODUCT_CATEGORY_ID = "product_category_id";

    /**
     * Product category name query parameter name
     */
    public static final String PRODUCT_CATEGORY_NAME = "product_category_name";

     /**
     * Map values from sql result set to product category object method
     *
     * @param resultSet sql result set with necessary values
     * @param numRows number of rows in result set
     * @return New ProductCategory object
     * @throws SQLException If can't extract values from result set
     */

    @Override
    public ProductCategory mapRow(ResultSet resultSet, int numRows) throws SQLException {

        ProductCategory productCategory = new ProductCategory();

        productCategory.setProductCategoryId(resultSet.getInt(PRODUCT_CATEGORY_ID));
        productCategory.setProductCategoryName(resultSet.getString(PRODUCT_CATEGORY_NAME));

        return productCategory;
    }
}
