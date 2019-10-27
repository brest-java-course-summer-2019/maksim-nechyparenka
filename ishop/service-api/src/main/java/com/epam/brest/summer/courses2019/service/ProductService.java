package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.model.Product;
import com.epam.brest.summer.courses2019.model.stub.ProductStub;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    /**
     * Find all Products in DataBase.
     *
     * @return Products.
     */
    List<Product> findAll();

    /**
     * Find all ProductStubs in DataBase.
     *
     * @return ProductStubs.
     */
    List<ProductStub> findAllStubs();

    /**
     * Find Product By Id.
     *
     * @param id id
     * @return Product
     */
    Product findById(Integer id);

    /**
     * Find ProductStub By Id.
     *
     * @param id id
     * @return ProductStub
     */
    ProductStub findStubById(Integer id);

    /**
     * Find Products By category Id.
     *
     * @param productCategoryId productCategoryId
     * @return Products
     */
    List<Product> findByProductCategoryId(Integer productCategoryId);

    /**
     * Find ProductStubs By category Id.
     *
     * @param productCategoryId productCategoryId
     * @return ProductStubs
     */
    List<ProductStub> findStubsByProductCategoryId(Integer productCategoryId);

    /**
     * Persist new product.
     *
     * @param product product
     * @return product
     */
    Product add(Product product);

    /**
     * Update Product.
     *
     * @param product product
     */
    void update(Product product);

    /**
     * Delete Product.
     *
     * @param id product id
     */
    void delete(Integer id);

    /**
     * Returns all products that fits price interval in specified category
     *
     * @param priceStart Price starting the price interval
     * @param priceEnd Price ending the price interval
     * @param productCategoryId Product category name
     * @return ProductStubs as {@code Stream}
     */
    List<ProductStub> findProductStubsFromPriceIntervalInCategory(BigDecimal priceStart, BigDecimal priceEnd,
                                                                  Integer productCategoryId);
}
