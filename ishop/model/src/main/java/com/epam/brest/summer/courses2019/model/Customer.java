package com.epam.brest.summer.courses2019.model;

import java.math.BigDecimal;
import java.util.Date;

public class Customer {

    private Integer customerId;
    private String customerFirstName;
    private String customerLastName;
    private Date registrationDate;
    private String customerLogin;
    private String customerPassword;
    private String customerCardNumber;


    public Integer getCustomerId() {
        return customerId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public Date getRegistrationDate() {
        return registrationDate;
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


    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
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

    public String toString() {
        return "Customer{"
                + "customerId=" + customerId
                + ", customerFirstName='" + customerFirstName + '\''
                + ", customerLastName=" + customerLastName + '\''
                + ", registrationDate=" + registrationDate + '\''
                + ", customerLogin=" + customerLogin + '\''
                + ", customerPassword=" + customerPassword + '\''
                + ", customerCardNumber=" + customerCardNumber
                + '}';
    }
}
