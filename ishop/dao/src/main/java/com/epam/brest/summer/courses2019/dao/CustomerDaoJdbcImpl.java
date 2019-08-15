package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Customer;
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerDaoJdbcImpl implements CustomerDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final static String SELECT_ALL =
            "SELECT customer_id, first_name, last_name, registration_date, login, password, "
                    + "card_number, customer_category_id FROM customer ORDER BY 2";

    private static final String FIND_BY_ID =
            "SELECT customer_id, first_name, last_name, registration_date, login, password, card_number, "
                    + "customer_category_id FROM customer WHERE customer_id = :customerId";

    private static final String FIND_BY_CUSTOMER_CATEGORY_ID =
            "SELECT customer_id, first_name, last_name, registration_date, login, password, card_number, "
                    + "customer_category_id FROM customer WHERE customer_category_id = :customerCategoryId";

    private final static String ADD_CUSTOMER =
            "INSERT INTO customer (first_name, last_name, registration_date, login, password, card_number, customer_category_id) "
                    + "VALUES (:customerFirstName, :customerLastName, :customerRegistrationDate, :customerLogin, :customerPassword, "
                    + ":customerCardNumber, :customerCategoryId)";

    private static final String UPDATE =
            "UPDATE customer SET first_name = :customerFirstName, last_name = :customerLastName, registration_date = :customerRegistrationDate, "
                    + "login = :customerLogin, password = :customerPassword, card_number = :customerCardNumber, customer_category_id = :customerCategoryId "
                    + "WHERE customer_id = :customerId";

    private static final String DELETE =
            "DELETE FROM customer WHERE customer_id = :customerId";

    private static final String CUSTOMER_CATEGORY_ID = "customerCategoryId";
    private static final String CUSTOMER_ID = "customerId";

    public CustomerDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers =
                namedParameterJdbcTemplate.query(SELECT_ALL, BeanPropertyRowMapper.newInstance(Customer.class));
        return customers;
    }

    @Override
    public List<Customer> findByCustomerCategoryId(Integer customerCategoryId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(CUSTOMER_CATEGORY_ID, customerCategoryId);
        List<Customer> results = namedParameterJdbcTemplate.query(FIND_BY_CUSTOMER_CATEGORY_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(Customer.class));
        return results;
    }

    @Override
    public Optional<Customer> findById(Integer customerId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(CUSTOMER_ID, customerId);
        List<Customer> results = namedParameterJdbcTemplate.query(FIND_BY_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(Customer.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public Customer add(Customer customer) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("firstName", customer.getCustomerFirstName());
        parameters.addValue("lastName", customer.getCustomerLastName());
        parameters.addValue("registrationDate", customer.getCustomerRegistrationDate());
        parameters.addValue("login", customer.getCustomerLogin());
        parameters.addValue("password", customer.getCustomerPassword());
        parameters.addValue("cardNumber", customer.getCustomerCardNumber());
        parameters.addValue("customerCategoryId", customer.getCustomerCategoryId());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_CUSTOMER, parameters, generatedKeyHolder);
        customer.setCustomerId(generatedKeyHolder.getKey().intValue());
        return customer;
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

//    private class CustomerRowMapper implements RowMapper<Customer> {
//        @Override
//        public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
//            Customer customer = new Customer();
//            customer.setCustomerId(resultSet.getInt("customer_id"));
//            customer.setCustomerFirstName(resultSet.getString("first_name"));
//            customer.setCustomerLastName(resultSet.getString("last_name"));
//            customer.setRegistrationDate(resultSet.getString("registration_date"));
//            customer.setCustomerLogin(resultSet.getString("login"));
//            customer.setCustomerPassword(resultSet.getString("password"));
//            customer.setCustomerCardNumber(resultSet.getString("card_number"));
//            customer.setCustomerCategoryId(resultSet.getInt("customer_category_id"));
//
//            return customer;
//        }
//    }
}
