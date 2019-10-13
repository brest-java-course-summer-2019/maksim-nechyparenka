package com.epam.brest.summer.courses2019.dao.mappers;

import com.epam.brest.summer.courses2019.model.dto.ProductDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Product Data Transfer Object Mapper
 *
 * @see RowMapper
 * @see ProductDTO
 * @author Maksim Nechyparenka
 */

@Component
public class ProductDtoRowMapper implements RowMapper<ProductDTO> {

    /**
     * Product Data Transfer Object id query parameter name
     */
    public static final String PRODUCT_DTO_ID = "product_id";

    /**
     * Product Data Transfer Object name query parameter name
     */
    public static final String PRODUCT_DTO_NAME = "product_name";

    /**
     * Product Data Transfer Object category id query parameter name
     */
    public static final String PRODUCT_DTO_CATEGORY_ID = "product_category_id";

    /**
     * Product Data Transfer Object category name query parameter name
     */
    public static final String PRODUCT_DTO_CATEGORY_NAME = "product_category_name";

    /**
     * Product Data Transfer Object price query parameter name
     */
    public static final String PRODUCT_DTO_PRICE = "product_price";

    /**
     * Map values from sql result set to product object method
     *
     * @param resultSet sql result set with necessary values
     * @param numRows number of rows in result set
     * @return New Product DTO object
     * @throws SQLException If can't extract values from result set
     */

    @Override
    public ProductDTO mapRow(ResultSet resultSet, int numRows) throws SQLException {

        ProductDTO productDTO = new ProductDTO();

        productDTO.setProductId(resultSet.getInt(PRODUCT_DTO_ID));
        productDTO.setProductName(resultSet.getString(PRODUCT_DTO_NAME));
        productDTO.setProductCategoryId(resultSet.getInt(PRODUCT_DTO_CATEGORY_ID));
        productDTO.setProductCategoryName(resultSet.getString(PRODUCT_DTO_CATEGORY_NAME));
        productDTO.setProductPrice(resultSet.getBigDecimal(PRODUCT_DTO_PRICE));

        return productDTO;
    }
}
