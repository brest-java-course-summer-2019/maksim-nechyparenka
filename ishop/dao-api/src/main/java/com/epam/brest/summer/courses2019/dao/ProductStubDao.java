package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.stub.ProductStub;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductStubDao {

    /**
     * Returns all producStubs
     *
     * @return ProductStubs as {@code List}
     */
    List<ProductStub> findAllStubs();

    /**
     * Returns ProductStub with defined id.
     *
     * @param productId Id of ProductStub to find
     * @return {@code Optional} describing the value of product found
     */
    Optional<ProductStub> findStubById(Integer productId);

    /**
     * Returns all productStubs from DataBase by category id.
     *
     * @return ProductStubs as {@code List}
     */
    List<ProductStub> findStubsByProductCategoryId(Integer productCategoryId);

    /**
     * Returns all products that fits price interval in specified category
     *
     * @param priceStart Price starting the price interval
     * @param priceEnd Price ending the price interval
     * @param productCategoryId Product category name
     * @return ProductStubs as {@code List}
     */
    List<ProductStub> findProductStubsFromPriceIntervalInCategory(BigDecimal priceStart, BigDecimal priceEnd,
                                                                    Integer productCategoryId);
}
