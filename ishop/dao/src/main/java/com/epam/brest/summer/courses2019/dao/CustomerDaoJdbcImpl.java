package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final String SELECT_ALL =
            "SELECT customer_id, first_name, last_name, registration_date, login, pass_word, "
                    + "card_number, customer_category_id FROM customer ORDER BY 2, 3";

    private static final String FIND_BY_ID =
            "SELECT customer_id, first_name, last_name, registration_date, login, pass_word, card_number, "
                    + "customer_category_id FROM customer WHERE customer_id = :customerId";

    private static final String FIND_BY_CUSTOMER_CATEGORY_ID =
            "SELECT customer_id, first_name, last_name, registration_date, login, pass_word, card_number, "
                    + "customer_category_id FROM customer WHERE customer_category_id = :customerCategoryId";

    private static final String ADD_CUSTOMER =
            "INSERT INTO customer (first_name, last_name, registration_date, login, pass_word, card_number, "
            + "customer_category_id) VALUES (:customerFirstName, :customerLastName, :customerRegistrationDate, "
            + ":customerLogin, :customerPassword, :customerCardNumber, :customerCategoryId)";

    private static final String UPDATE =
            "UPDATE customer SET first_name = :customerFirstName, last_name = :customerLastName, "
            + "registration_date = :customerRegistrationDate, login = :customerLogin, pass_word = :customerPassword, "
            + "card_number = :customerCardNumber, customer_category_id = :customerCategoryId "
            + "WHERE customer_id = :customerId";

    private static final String DELETE =
            "DELETE FROM customer WHERE customer_id = :customerId";

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
        namedParameterJdbcTemplate.update(ADD_CUSTOMER, parameters, generatedKeyHolder);
        customer.setCustomerId(generatedKeyHolder.getKey().intValue());
        return customer;
    }

    @Override
    public List<Customer> findAll() {

        LOGGER.debug("findAll({})");

        List<Customer> allCustomers = namedParameterJdbcTemplate.query(SELECT_ALL, new CustomerRowMapper());
        return allCustomers;
    }

    @Override
    public Optional<Customer> findById(Integer customerId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(CUSTOMER_ID, customerId);
        List<Customer> results = namedParameterJdbcTemplate.query(FIND_BY_ID, namedParameters,
                new CustomerRowMapper());
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public List<Customer> findByCustomerCategoryId(Integer customerCategoryId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(CUSTOMER_CATEGORY_ID, customerCategoryId);
        List<Customer> results = namedParameterJdbcTemplate.query(FIND_BY_CUSTOMER_CATEGORY_ID, namedParameters,
                new CustomerRowMapper());
        return results;
    }

    @Override
    public void update(Customer customer) {
        Optional.of(namedParameterJdbcTemplate.update(UPDATE, new BeanPropertySqlParameterSource(customer)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update Customer in DataBase!"));
    }

    @Override
    public void delete(Integer customerId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(CUSTOMER_ID, customerId);
        Optional.of(namedParameterJdbcTemplate.update(DELETE, mapSqlParameterSource))
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
