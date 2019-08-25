package com.epam.brest.summer.courses2019.model;

public class ProductCategory {

    private Integer productCategoryId;
    private String productCategoryName;

    public ProductCategory() {

    }

    public ProductCategory(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public String toString() {
        return "ProductCategory{"
                + "productCategoryId=" + productCategoryId
                + ", productCategoryName='" + productCategoryName
                + '}';
    }
}
