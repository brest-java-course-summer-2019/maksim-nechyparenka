package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.CustomerCategory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
public class CustomerCategoryDaoJdbcImpl implements CustomerCategoryDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${customerCategory.add}")
    private String addCustomerCategorySql;

    @Value("${customerCategory.findAll}")
    private String findAllCustomersCategorySql;

    @Value("${customerCategory.findCustomerCategoryById}")
    private String findCustomerCategoryByIdSql;

    @Value("${customerCategory.update}")
    private String updateCustomersCategorySql;

    @Value("${customerCategory.delete}")
    private String deleteCustomerCategorySql;

    private final static String CUSTOMER_CATEGORY_ID = "customerCategoryId";
    private final static String CUSTOMER_CATEGORY_NAME = "customerCategoryName";

    public CustomerCategoryDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public CustomerCategory add(CustomerCategory customerCategory) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(CUSTOMER_CATEGORY_NAME, customerCategory.getCustomerCategoryName());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(addCustomerCategorySql, parameters, generatedKeyHolder);
        customerCategory.setCustomerCategoryId(generatedKeyHolder.getKey().intValue());
        return customerCategory;
    }

    @Override
    public List<CustomerCategory> findAll() {

        List<CustomerCategory> allCustomers = namedParameterJdbcTemplate.query(findAllCustomersCategorySql,
                BeanPropertyRowMapper.newInstance(CustomerCategory.class));
        return allCustomers;
    }

    @Override
    public Optional<CustomerCategory> findCustomerCategoryById(Integer customerCategoryId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(CUSTOMER_CATEGORY_ID, customerCategoryId);
        List<CustomerCategory> result = namedParameterJdbcTemplate.query(findCustomerCategoryByIdSql, namedParameters,
                BeanPropertyRowMapper.newInstance(CustomerCategory.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(result));
    }

    @Override
    public void update(CustomerCategory customerCategory) {
        Optional.of(namedParameterJdbcTemplate.update(updateCustomersCategorySql,
                new BeanPropertySqlParameterSource(customerCategory)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update Customer Category in DataBase!"));
    }

    @Override
    public void delete(Integer customerCategoryId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(CUSTOMER_CATEGORY_ID, customerCategoryId);
        Optional.of(namedParameterJdbcTemplate.update(deleteCustomerCategorySql, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete Customer Category from DataBase!"));
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }
}
