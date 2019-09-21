package com.epam.brest.summer.courses2019.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class ProductDTO {

    /**
     * {@code product DTO} id
     */
    private Integer productId;

    /**
     * String representing {@code product} name
     */
    private String productName;

    /**
     * String representing {@code category} name
     */
    private String productCategoryName;

    /**
     * Amount of products in {@code category}
     */
    private BigDecimal productQuantity;

    /**
     * Date when product have been added
     */
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate productReceiptDate;;

    /**
     * {@code Category id} to which product belongs
     */
    private Integer productCategoryId;

    /**
     * Product price
     */

    private BigDecimal productPrice;

    /**
     * Returns product DTOs id
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
     * Returns {@code category} name
     *
     * @return {@code String} representing category name
     */
    public String getProductCategoryName() {
        return productCategoryName;
    }

    /**
     * Returns {@code product} quantity
     *
     * @return {@code Integer} as product quantity
     */
    public BigDecimal getProductQuantity() {
        return productQuantity;
    }

    /**
     * Returns date when product was added
     *
     * @return {@code LocalDate} as date when added
     */
    public LocalDate getProductReceiptDate() {
        return productReceiptDate;
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
     * Returns {@code category} id
     *
     * @return {@code Integer} as product category id
     */
    public Integer getProductCategoryId() {
        return productCategoryId;
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
     * Sets category name
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
     * Sets product quantity
     *
     * @param productQuantity product amount as {@code Integer}
     */
    public void setProductQuantity(BigDecimal productQuantity) {
        this.productQuantity = productQuantity;
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
     * Sets date when product was added
     *
     * @param productReceiptDate date added as {@code LocalDate}
     */
    public void setProductReceiptDate(LocalDate productReceiptDate) {
        this.productReceiptDate = productReceiptDate;
    }

    /**
     * Sets product category id
     *
     * @param productCategoryId category id as {@code Integer}
     */
    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
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
        ProductDTO productDTO = (ProductDTO) object;
        return productId.equals(productDTO.productId) &&
                productCategoryName.equals(productDTO.productCategoryName) &&
                productName.equals(productDTO.productName) &&
                productQuantity.equals(productDTO.productQuantity) &&
                productReceiptDate.equals(productDTO.productReceiptDate) &&
                productCategoryId.equals(productDTO.productCategoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productCategoryName, productName, productQuantity, productReceiptDate,
                productCategoryId);
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", categoryName='" + productCategoryName + '\'' +
                ", productQuantity=" + productQuantity +
                ", productReceiptDate=" + productReceiptDate +
                ", productCategoryId=" + productCategoryId +
                '}';
    }
}