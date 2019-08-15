package com.epam.brest.summer.courses2019.model;

import java.math.BigDecimal;

public class Product {

    private Integer productId;
    private String productName;
    private Integer productCategoryId;
    private String productReceiptDate;
    private BigDecimal productQuantity;
    private BigDecimal productPrice;

    public Product() {

    }

    public Product(String productName, Integer productCategoryId, String productReceiptDate, BigDecimal productQuantity, BigDecimal productPrice) {
        this.productName = productName;
        this.productCategoryId = productCategoryId;
        this.productReceiptDate = productReceiptDate;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
    }

    public Product(String productName) {
        this.productName = productName;
    }

    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public String getProductReceiptDate() {
        return productReceiptDate;
    }

    public BigDecimal getProductQuantity() {
        return productQuantity;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public void setProductReceiptDate(String productReceiptDate) {
        this.productReceiptDate = productReceiptDate;
    }

    public void setProductQuantity(BigDecimal productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String toString() {
        return "Product{"
                + "productId=" + productId
                + ", productName='" + productName + '\''
                + ", productCategory=" + productCategoryId + '\''
                + ", receiptDate=" + productReceiptDate + '\''
                + ", productQuantity" + productQuantity + '\''
                + ", productPrice=" + productPrice
                + '}';
    }
}
