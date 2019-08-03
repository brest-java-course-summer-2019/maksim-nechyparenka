package com.epam.brest.summer.courses2019.model;

public class CustomerCategory {

    private Integer customerCategoryId;
    private String customerCategoryName;


    public Integer getCustomerCategoryId() {
        return customerCategoryId;
    }

    public String getCustomerCategoryName() {
        return customerCategoryName;
    }

    public void setCustomerCategoryId(Integer customerCategoryId) {
        this.customerCategoryId = customerCategoryId;
    }

    public void setCustomerCategoryName(String customerCategoryName) {
        this.customerCategoryName = customerCategoryName;
    }
}