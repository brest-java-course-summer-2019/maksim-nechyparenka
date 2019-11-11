package com.epam.brest.summer.courses2019.web_app;

import com.epam.brest.summer.courses2019.model.ProductCategory;
import com.epam.brest.summer.courses2019.service.ProductCategoryService;
import com.epam.brest.summer.courses2019.web_app.validator.ProductCategoryValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

public class ProductCategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCategoryController.class);

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    ProductCategoryValidator productCategoryValidator;

    /**
     * Go to page with list of all product categories
     *
     * @param model model contains information for view rendering
     * @return view template name
     */
    @GetMapping(value = "/categories")
    public final String categories(Model model) {

        LOGGER.debug("Find all product categories ({})", model);

        model.addAttribute("productCategories", productCategoryService.findAll());
        return "categories";

    }

    /**
     * Go to add new product category
     *
     * @param model model contains information for view rendering
     * @return view template name
     */
    @GetMapping(value = "/category")
    public final String gotoAddProductCategory(Model model) {

        LOGGER.debug("Goto Add new product category ({})", model);

        ProductCategory productCategory = new ProductCategory();
        model.addAttribute("isNew", true);
        model.addAttribute("category", productCategory);
        model.addAttribute("productCategories", productCategoryService.findAll());
        return "category";
    }

    /**
     * Save new {@code product category} if it is valid
     * and redirect to page with list of product categories
     *
     * @param productCategory product category to save
     * @param result result object contains information about product category validation
     * @param model model contains information for view rendering
     * @return view template name
     */
    @PostMapping(value = "/category")
    public final String addProductCategory(@Valid ProductCategory productCategory, BindingResult result, Model model) {

        LOGGER.debug("Add product category ({})", model);

        productCategoryValidator.validate(productCategory, result);

        if (result.hasErrors()) {
            model.addAttribute("productCategories", productCategoryService.findAll());
            return "category";
        } else {
            productCategoryService.add(productCategory);
            return "redirect:/categories";
        }
    }

    /**
     * Updates already existing {@code productCategory} by new one if it is valid
     * and redirect to page with list of product categories
     *
     * @param productCategory product category for update
     * @param result result object contains information about validation
     * @param model model contains information for view rendering
     * @return view template name
     */
    @PostMapping(value = "/category/{id}")
    public final String updateCategory(@Valid ProductCategory productCategory, BindingResult result, Model model) {

        LOGGER.debug("Update product category ({}, {})", productCategory, result);

        productCategoryValidator.validate(productCategory, result);

        if (result.hasErrors()) {
            model.addAttribute("productCategories", productCategoryService.findAll());
            return "category";
        } else {
            productCategoryService.update(productCategory);
            return "redirect:/categories";
        }
    }

    /**
     * Delete product category by id
     *
     * @param id product category id to delete
     * @return redirect to product categories template page
     */
    @GetMapping(value = "/categories/{id}")
    public final String deleteCategory(@PathVariable Integer id, Model model) {

        LOGGER.debug("Delete product category by id ({}, {})", id, model);

        productCategoryService.delete(id);
        return "redirect:/categories";
    }
}