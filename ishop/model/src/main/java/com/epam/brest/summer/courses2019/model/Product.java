package com.epam.brest.summer.courses2019.model;

import java.math.BigDecimal;
import java.util.Date;

public class Product {

    private Integer productId;
    private String productName;
    private String productCategory;
    private String receiptDate;
    private BigDecimal productQuantity;
    private BigDecimal productPrice;

    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public String getReceiptDate() {
        return receiptDate;
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

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
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
                + ", productCategory=" + productCategory + '\''
                + ", receiptDate=" + receiptDate + '\''
                + ", productQuantity" + productQuantity + '\''
                + ", productPrice=" + productPrice
                + '}';
    }
}
