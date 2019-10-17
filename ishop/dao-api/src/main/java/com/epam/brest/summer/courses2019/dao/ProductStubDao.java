package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.stub.ProductStub;

import java.math.BigDecimal;
import java.util.List;

public interface ProductStubDao {

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
