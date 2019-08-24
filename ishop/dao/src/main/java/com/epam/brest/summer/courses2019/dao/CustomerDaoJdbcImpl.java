package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerDaoJdbcImpl implements CustomerDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${customer.findAll}")
    private String findAllCustomersSql;

    @Value("${customer.findById}")
    private String findByCustomerIdSql;

    @Value("${customer.findByCustomerCategoryId}")
    private String findByCustomerCategoryIdSql;

    @Value("${customer.add}")
    private String addCustomerSql;

    @Value("${customer.update}")
    private String updateCustomerSql;

    @Value("${customer.delete}")
    private String deleteCustomerSql;

    private static final String CUSTOMER_ID = "customerId";
    private static final String CUSTOMER_FIRST_NAME = "customerFirstName";
    private static final String CUSTOMER_LAST_NAME = "customerLastName";
    private static final String CUSTOMER_REGISTRATION_DATE = "customerRegistrationDate";
    private static final String CUSTOMER_LOGIN = "customerLogin";
    private static final String CUSTOMER_PASSWORD = "customerPassword";
    private static final String CUSTOMER_CARD_NUMBER = "customerCardNumber";
    private static final String CUSTOMER_CATEGORY_ID = "customerCategoryId";

    /**
     * Default logger for current class
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerDaoJdbcImpl.class);

    public CustomerDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Customer add(Customer customer) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(CUSTOMER_FIRST_NAME, customer.getCustomerFirstName());
        parameters.addValue(CUSTOMER_LAST_NAME, customer.getCustomerLastName());
        parameters.addValue(CUSTOMER_REGISTRATION_DATE, customer.getCustomerRegistrationDate());
        parameters.addValue(CUSTOMER_LOGIN, customer.getCustomerLogin());
        parameters.addValue(CUSTOMER_PASSWORD, customer.getCustomerPassword());
        parameters.addValue(CUSTOMER_CARD_NUMBER, customer.getCustomerCardNumber());
        parameters.addValue(CUSTOMER_CATEGORY_ID, customer.getCustomerCategoryId());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(addCustomerSql, parameters, generatedKeyHolder);
        customer.setCustomerId(generatedKeyHolder.getKey().intValue());
        return customer;
    }

    @Override
    public List<Customer> findAll() {

        LOGGER.debug("findAll({})");

        List<Customer> allCustomers = namedParameterJdbcTemplate.query(findAllCustomersSql, new CustomerRowMapper());
        return allCustomers;
    }

    @Override
    public Optional<Customer> findById(Integer customerId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(CUSTOMER_ID, customerId);
        List<Customer> results = namedParameterJdbcTemplate.query(findByCustomerIdSql, namedParameters,
                new CustomerRowMapper());
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public List<Customer> findByCustomerCategoryId(Integer customerCategoryId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(CUSTOMER_CATEGORY_ID, customerCategoryId);
        List<Customer> results = namedParameterJdbcTemplate.query(findByCustomerCategoryIdSql, namedParameters,
                new CustomerRowMapper());
        return results;
    }

    @Override
    public void update(Customer customer) {
        Optional.of(namedParameterJdbcTemplate.update(updateCustomerSql, new BeanPropertySqlParameterSource(customer)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update Customer in DataBase!"));
    }

    @Override
    public void delete(Integer customerId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(CUSTOMER_ID, customerId);
        Optional.of(namedParameterJdbcTemplate.update(deleteCustomerSql, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete Customer from DataBase!"));
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    private class CustomerRowMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet resultSet, int i) throws SQLException {

            Customer customer = new Customer();

            customer.setCustomerId(resultSet.getInt("customer_id"));
            customer.setCustomerFirstName(resultSet.getString("first_name"));
            customer.setCustomerLastName(resultSet.getString("last_name"));
            customer.setCustomerRegistrationDate(resultSet.getDate("registration_date").toLocalDate());
            customer.setCustomerLogin(resultSet.getString("login"));
            customer.setCustomerPassword(resultSet.getString("pass_word"));
            customer.setCustomerCardNumber(resultSet.getString("card_number"));
            customer.setCustomerCategoryId(resultSet.getInt("customer_category_id"));

            return customer;
        }
    }
}
