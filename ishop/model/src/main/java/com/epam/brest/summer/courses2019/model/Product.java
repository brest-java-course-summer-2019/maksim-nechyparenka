package com.epam.brest.summer.courses2019.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Product {

    /**
     * Product id
     */

    private Integer productId;

    /**
     * Product name
     */

    private String productName;

    /**
     * Category id in which product exists
     */

    private Integer productCategoryId;

    /**
     * {@code LocalDate} as date when product was added
     */

    private LocalDate productReceiptDate;

    /**
     * Product quantity
     */

    private BigDecimal productQuantity;

    /**
     * Product price
     */

    private BigDecimal productPrice;

    public Product() {

    }

    public Product(String productName, Integer productCategoryId, LocalDate productReceiptDate, BigDecimal productQuantity, BigDecimal productPrice) {
        this.productName = productName;
        this.productCategoryId = productCategoryId;
        this.productReceiptDate = productReceiptDate;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
    }

    /**
     * Returns product id
     *
     * @return {@code Integer} as product id
     */

    public Integer getProductId() {
        return productId;
    }

    /**
     * Returns {@code product} name
     *
     * @return {@code String} as product name
     */

    public String getProductName() {
        return productName;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public LocalDate getProductReceiptDate() {
        return productReceiptDate;
    }

    /**
     * Returns {@code product} quantity
     *
     * @return {@code BigDecimal} as product quantity
     */

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

    public void setProductReceiptDate(LocalDate productReceiptDate) {
        this.productReceiptDate = productReceiptDate;
    }

    public void setProductQuantity(BigDecimal productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * Compare products method
     *
     * @param object category id as {@code Object}
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Product product = (Product) object;
        return productId.equals(product.productId) &&
                productCategoryId.equals(product.productCategoryId) &&
                productName.equals(product.productName) &&
                productQuantity.equals(product.productQuantity) &&
                productReceiptDate.equals(product.productReceiptDate) &&
                productCategoryId.equals(product.productCategoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productCategoryId, productName, productQuantity, productReceiptDate,
                productCategoryId);
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
