package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.dao.mappers.ProductCategoryRowMapper;
import com.epam.brest.summer.courses2019.model.ProductCategory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public class ProductCategoryDaoJdbcImpl implements ProductCategoryDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final ProductCategoryRowMapper productCategoryRowMapper;

    @Value("${productCategory.add}")
    private String addProductCategorySql;

    @Value("${productCategory.findAll}")
    private String findAllProductsCategoriesSql;

    @Value("${productCategory.findProductCategoryById}")
    private String findProductCategoryByIdSql;

    @Value("${productCategory.update}")
    private String updateProductCategorySql;

    @Value("${productCategory.delete}")
    private String deleteProductCategorySql;

    private final static String PRODUCT_CATEGORY_ID = "productCategoryId";
    private final static String PRODUCT_CATEGORY_NAME = "productCategoryName";

    public ProductCategoryDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                      ProductCategoryRowMapper productCategoryRowMapper) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.productCategoryRowMapper = productCategoryRowMapper;
    }

    @Override
    public ProductCategory add(ProductCategory productCategory) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(PRODUCT_CATEGORY_NAME, productCategory.getProductCategoryName());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(addProductCategorySql, parameters, generatedKeyHolder);
        productCategory.setProductCategoryId(generatedKeyHolder.getKey().intValue());
        return productCategory;
    }

    @Override
    public List<ProductCategory> findAll() {

        List<ProductCategory> allProductCategories = namedParameterJdbcTemplate.query(findAllProductsCategoriesSql,
                productCategoryRowMapper);
        return allProductCategories;
    }

    @Override
    public Optional<ProductCategory> findProductCategoryById(Integer productCategoryId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(PRODUCT_CATEGORY_ID, productCategoryId);
        ProductCategory result = namedParameterJdbcTemplate.queryForObject(findProductCategoryByIdSql, namedParameters,
                productCategoryRowMapper);
        return Optional.ofNullable(result);
    }

    @Override
    public void update(ProductCategory productCategory) {
        Optional.of(namedParameterJdbcTemplate.update(updateProductCategorySql,
                new BeanPropertySqlParameterSource(productCategory)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update Product Category in DataBase!"));
    }

    @Override
    public void delete(Integer productCategoryId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(PRODUCT_CATEGORY_ID, productCategoryId);
        Optional.of(namedParameterJdbcTemplate.update(deleteProductCategorySql, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete Product Category from DataBase!"));
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }
}

