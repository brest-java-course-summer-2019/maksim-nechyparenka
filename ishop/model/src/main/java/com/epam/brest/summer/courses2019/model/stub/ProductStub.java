package com.epam.brest.summer.courses2019.model.stub;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductStub {

    /**
     * {@code product Stub} id
     */
    private Integer productId;

    /**
     * String representing {@code product} name
     */
    private String productName;

    /**
     * String representing {@code productCategory} id
     */
    private Integer productCategoryId;

    /**
     * String representing {@code productCategory} name
     */
    private String productCategoryName;

    /**
     * Product price
     */
    private BigDecimal productPrice;

    public ProductStub() {

    }

    public ProductStub(Integer productId, String productName, Integer productCategoryId, String productCategoryName,
                      BigDecimal productPrice) {

        this.productId = productId;
        this.productName = productName;
        this.productCategoryId = productCategoryId;
        this.productCategoryName = productCategoryName;
        this.productPrice = productPrice;
    }

    /**
     * Returns Product id
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

    /**
     * Returns {@code productCategory} id
     *
     * @return {@code Integer} representing category id
     */
    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    /**
     * Returns {@code productCategory} name
     *
     * @return {@code String} representing category name
     */
    public String getProductCategoryName() {
        return productCategoryName;
    }

    /**
     * Returns product price
     *
     * @return {@code LocalDate} as date when added
     */
    public BigDecimal getProductPrice() {
        return productPrice;
    }

    /**
     * Sets product id
     *
     * @param productId product id {@code Integer value}
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * Sets product category id
     *
     * @param productCategoryId category id {@code Integer}
     */
    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    /**
     * Sets product category name
     *
     * @param productCategoryName category name {@code String}
     */
    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    /**
     * Sets product name
     *
     * @param productName product name as {@code String}
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Sets product price
     *
     * @param productPrice product price as {@code BigDecimal}
     */
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * Compare method for products
     *
     * @param object category id as {@code Object}
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ProductStub productStub = (ProductStub) object;
        return productId.equals(productStub.productId) &&
               productName.equals(productStub.productName) &&
               productCategoryId.equals(productStub.productCategoryId) &&
               productCategoryName.equals(productStub.productCategoryName) &&
               productPrice.equals(productStub.productPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, productCategoryId, productCategoryName, productPrice);
    }

    @Override
    public String toString() {
        return "ProductStub{" +
                "productId=" + productId + '\'' +
                ", productName=" + productName + '\'' +
                ", categoryId=" + productCategoryId + '\'' +
                ", categoryName=" + productCategoryName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}