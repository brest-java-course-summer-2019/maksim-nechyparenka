package com.epam.brest.summer.courses2019.model;

import java.time.LocalDate;

/**
 * Customer model
 */

public class Customer {

    private Integer customerId;
    private String customerFirstName;
    private String customerLastName;
    private LocalDate customerRegistrationDate;
    private String customerLogin;
    private String customerPassword;
    private String customerCardNumber;
    private Integer customerCategoryId;

    public Customer() {

    }

    public Customer(String customerFirstName, String customerLastName, LocalDate customerRegistrationDate, String customerLogin,
                    String customerPassword, String customerCardNumber, Integer customerCategoryId) {
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerRegistrationDate = customerRegistrationDate;
        this.customerLogin = customerLogin;
        this.customerPassword = customerPassword;
        this.customerCardNumber = customerCardNumber;
        this.customerCategoryId = customerCategoryId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public LocalDate getCustomerRegistrationDate() {
        return customerRegistrationDate;
    }

    public String getCustomerLogin() {
        return customerLogin;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public String getCustomerCardNumber() {
        return customerCardNumber;
    }

    public Integer getCustomerCategoryId() {
        return customerCategoryId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public void setCustomerRegistrationDate(LocalDate customerRegistrationDate) {
        this.customerRegistrationDate = customerRegistrationDate;
    }

    public void setCustomerLogin(String customerLogin) {
        this.customerLogin = customerLogin;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public void setCustomerCardNumber(String customerCardNumber) {
        this.customerCardNumber = customerCardNumber;
    }

    public void setCustomerCategoryId(Integer customerCategoryId) {
        this.customerCategoryId = customerCategoryId;
    }

    public String toString() {
        return "Customer{"
                + "customerId=" + customerId
                + ", customerFirstName='" + customerFirstName + '\''
                + ", customerLastName=" + customerLastName + '\''
                + ", registrationDate=" + customerRegistrationDate + '\''
                + ", customerLogin=" + customerLogin + '\''
                + ", customerPassword=" + customerPassword + '\''
                + ", customerCardNumber=" + customerCardNumber + '\''
                + ", customerCategoryId=" + customerCategoryId
                + '}';
    }
}
