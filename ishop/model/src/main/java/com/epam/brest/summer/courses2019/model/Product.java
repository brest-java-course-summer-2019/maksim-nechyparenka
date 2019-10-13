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
     * Category name in which product exists
     */

    private String productCategoryName;

    /**
     * Product supplier name
     */

    private String productSupplierName;

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

    public Product(String productName, Integer productCategoryId, String productCategoryName, String productSupplierName,
                   LocalDate productReceiptDate, BigDecimal productQuantity, BigDecimal productPrice) {
        this.productName = productName;
        this.productCategoryId = productCategoryId;
        this.productCategoryName = productCategoryName;
        this.productSupplierName = productSupplierName;
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

    /**
     * Returns {@code product} category id
     *
     * @return {@code Integer} as product category id
     */
    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    /**
     * Returns {@code product} category name
     *
     * @return {@code String} as product category name
     */
    public String getProductCategoryName() {
        return productCategoryName;
    }

    /**
     * Returns {@code product} supplier name
     *
     * @return {@code String} as product supplier name
     */
    public String getProductSupplierName() {
        return productSupplierName;
    }

    /**
     * Returns {@code product} receipt date
     *
     * @return {@code LocalDate} as product receipt date
     */
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

    /**
     * Returns {@code product} price
     *
     * @return {@code BigDecimal} as product price
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
     * Sets product name
     *
     * @param productName product name as {@code String}
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Sets product category id
     *
     * @param productCategoryId product category id {@code Integer value}
     */
    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    /**
     * Sets product category name
     *
     * @param productCategoryName product category name {@code String value}
     */
    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    /**
     * Sets product supplier name
     *
     * @param productSupplierName product supplier name as {@code String}
     */
    public void setProductSupplierName(String productSupplierName) {
        this.productSupplierName = productSupplierName;
    }

    /**
     * Sets product receipt date
     *
     * @param productReceiptDate product receipt date {@code LocalDate value}
     */
    public void setProductReceiptDate(LocalDate productReceiptDate) {
        this.productReceiptDate = productReceiptDate;
    }

    /**
     * Sets product quantity
     *
     * @param productQuantity product quantity {@code BigDecimal value}
     */
    public void setProductQuantity(BigDecimal productQuantity) {
        this.productQuantity = productQuantity;
    }

    /**
     * Sets product price
     *
     * @param productPrice product price {@code BigDecimal value}
     */
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
               productName.equals(product.productName) &&
               productCategoryId.equals(product.productCategoryId) &&
               productCategoryName.equals(product.productCategoryName) &&
               productSupplierName.equals(product.productSupplierName) &&
               productQuantity.equals(product.productQuantity) &&
               productReceiptDate.equals(product.productReceiptDate) &&
               productPrice.equals(product.productPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productCategoryId, productName, productCategoryName, productQuantity,
                productReceiptDate, productCategoryId);
    }

    public String toString() {
        return "Product{"
                + "productId=" + productId
                + ", productName='" + productName + '\''
                + ", productCategoryId=" + productCategoryId + '\''
                + ", productCategoryName=" + productCategoryName + '\''
                + ", productSupplier=" + productSupplierName + '\''
                + ", receiptDate=" + productReceiptDate + '\''
                + ", productQuantity" + productQuantity + '\''
                + ", productPrice=" + productPrice
                + '}';
    }
}
