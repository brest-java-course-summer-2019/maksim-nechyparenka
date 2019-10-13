package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Product;
import com.epam.brest.summer.courses2019.model.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductDao {

    /**
     * Save product to database. Returns inserted Product with generated id.
     *
     * @param product Product object to save in database
     * @return {@code Optional} Product with generated id
     */
    Product add(Product product);

    /**
     * Update already existing product object by new object.
     *
     * @param product Object to replace older
     */
    void update(Product product);

    /**
     * Delete product from database by defined id.
     *
     * @param productId Product id to delete
     */
    void delete(Integer productId);

    /**
     * Returns all products found in DataBase.
     *
     * @return Products as {@code List}
     */
    List<Product> findAll();


    Optional<Product> findById(Integer productId);

    /**
     * Returns all products found in DataBase by category id.
     *
     * @return Products as {@code List}
     */
    List<Product> findByProductCategoryId(Integer productCategoryId);

    /**
     * Returns all DataTransferObjects(DTO) of products found in DataBase.
     *
     * @return Product Data Transfer Objects as {@code List}
     */
    List<ProductDTO> findAllProductDTOs();

    /**
     * Returns DataTransferObject(DTO) of product found in DataBase by id.
     *
     * @return Product Data Transfer Object as {@code Optional}
     */
    Optional<ProductDTO> findProductDtoById(Integer productId);

    /**
     * Returns all product DTOs found in DataBase by category id.
     *
     * @return Product DTOs as {@code List}
     */
    List<ProductDTO> findByProductDtoCategoryId(Integer productCategoryId);

    /**
     * Returns all product Data Transfer Objects that fits price interval in specified category
     *
     * @param priceStart Price starting the price interval
     * @param priceEnd Price ending the price interval
     * @param productCategoryId Product category name
     * @return Product DTOs as {@code Stream}
     */
    List<ProductDTO> findProductDTOsFromPriceIntervalInCategory(BigDecimal priceStart, BigDecimal priceEnd,
                                                                Integer productCategoryId);


}
