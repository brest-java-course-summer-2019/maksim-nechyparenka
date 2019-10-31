package com.epam.brest.summer.courses2019.model.stub;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * ProductStub for model.
 */
public class ProductStub {

    /**
     * Product Id {@code product Stub} id
     */
    private Integer productId;

    /**
     * Product Name {@code product} name
     */
    @NotEmpty(message = "Product name cannot be empty!")
    @Size(min = 1, max = 255, message = "Product name must be between 2 and 40 characters!")
    private String productName;

    /**
     * Product Category Id {@code productCategory} id
     */
    private Integer productCategoryId;

//    /**
//     * Product Category Name {@code productCategory} name
//     */
//    private String productCategoryName;

    /**
     * Product price {@code productPrice} price
     */
    @Digits(integer = 7, fraction = 0, message = "Not more 7 digits!")
    @NotEmpty
    @Positive
    @Min(value = 1)
    private BigDecimal productPrice;

    /**
     * ProductStub constructor without arguments
     */
    public ProductStub() {

    }

    /**
     * ProductStub constructor with arguments
     *
     * @param productName product name
     * @param productCategoryId product category id
     * @param productPrice product price
     */
    public ProductStub(String productName, Integer productCategoryId, BigDecimal productPrice) {

        this.productName = productName;
        this.productCategoryId = productCategoryId;
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

//    /**
//     * Returns {@code productCategory} name
//     *
//     * @return {@code String} representing category name
//     */
//    public String getProductCategoryName() {
//        return productCategoryName;
//    }

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

//    /**
//     * Sets product category name
//     *
//     * @param productCategoryName category name {@code String}
//     */
//    public void setProductCategoryName(String productCategoryName) {
//        this.productCategoryName = productCategoryName;
//    }

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
               productPrice.equals(productStub.productPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, productCategoryId, productPrice);
    }

    @Override
    public String toString() {
        return "ProductStub{" +
                "productId=" + productId + '\'' +
                ", productName=" + productName + '\'' +
                ", categoryId=" + productCategoryId + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}