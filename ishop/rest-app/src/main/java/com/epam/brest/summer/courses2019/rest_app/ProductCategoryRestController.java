package com.epam.brest.summer.courses2019.rest_app;

import com.epam.brest.summer.courses2019.model.ProductCategory;
import com.epam.brest.summer.courses2019.service.ProductCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class ProductCategoryRestController {

    /**
     * Logger for current class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);

    /**
     * ProductCategoryService object to get information of products categories
     */
    private ProductCategoryService productCategoryService;

    /**
     * Constructor with ProductCategoryService argument
     *
     * @param productCategoryService product category service object
     */
    @Autowired
    public ProductCategoryRestController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    /**
     * Returns all {@code productCategories} from DataBase
     *
     * @return {@code List} of a {@code productCategories}
     */
    @GetMapping(value = "")
    public List<ProductCategory> findAll() {

        LOGGER.debug("Get all product categories from DataBase");

        return productCategoryService.findAll();
    }

    /**
     * Returns a {@code productCategory} with specified id
     *
     * @param productCategoryId id of required {@code productCategory}
     * @return required {@code productCategory}
     */
    @GetMapping(value = "/{productCategoryId}")
    public ProductCategory findProductCategoryById(@PathVariable() Integer productCategoryId) {

        LOGGER.debug("Get product category by id = {}", productCategoryId);

        return productCategoryService.findProductCategoryById(productCategoryId);
    }

    /**
     * Saves product category to DataBase
     *
     * @param productCategory {@code productCategory} to save
     * @return save productCategory with generated id
     */
    @PostMapping
    public ProductCategory add(@RequestBody ProductCategory productCategory) {

        LOGGER.debug("Add product category {}", productCategory);

        return productCategoryService.add(productCategory);
    }

    /**
     * Updates existing {@code productCategory} with new one
     *
     * @param productCategory {@code productCategory} to update older one
     */
    @PutMapping(value = "/{id}")
    public void update(@RequestBody ProductCategory productCategory) {

        LOGGER.debug("Update product category in DataBase {}", productCategory);

        productCategoryService.update(productCategory);
    }

    /**
     * Deletes existing {@code productCategory} with specified product category id
     *
     * @param productCategoryId product category id to delete {@code productCategory}
     */
    @DeleteMapping(value = "/{productCategoryId}")
    public void deleteProductCategory(@PathVariable Integer productCategoryId) {

        LOGGER.debug("Delete product category ({})", productCategoryId);

        productCategoryService.delete(productCategoryId);
    }
}
