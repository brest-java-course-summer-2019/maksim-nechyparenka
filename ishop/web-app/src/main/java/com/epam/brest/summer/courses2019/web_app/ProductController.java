package com.epam.brest.summer.courses2019.web_app;

import com.epam.brest.summer.courses2019.model.Product;
import com.epam.brest.summer.courses2019.service.ProductCategoryService;
import com.epam.brest.summer.courses2019.service.ProductService;
import com.epam.brest.summer.courses2019.web_app.validator.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.math.BigDecimal;

/**
 * Product category controller.
 */
@Controller
public class ProductController {

    /**
     * Default logger for current class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    ProductValidator productValidator;

    /**
     * Constructs new object with parameters
     *
     * @param productService product service layer object to inject
     * @param productCategoryService category service layer object to inject
     * @param productValidator object for validate product to inject
     */
    @Autowired
    public ProductController(ProductService productService, ProductCategoryService productCategoryService,
            ProductValidator productValidator) {

        this.productService = productService;
        this.productCategoryService = productCategoryService;
        this.productValidator = productValidator;
    }

    /**
     * Goto Products list page.
     *
     * @param model model
     * @return view name
     */
    @GetMapping(value = "/products")
    public final String products(Model model) {

        LOGGER.debug("Find all products: ({})", model);

        model.addAttribute("products", productService.findAll());
        model.addAttribute("productCategories", productCategoryService.findAll());
        return "products";
    }

    /**
     * Goto edit existing Product page by Id.
     *
     * @return view name
     */
    @GetMapping(value = "/products/{id}")
    public final String gotoEditExistingProductPage(@PathVariable Integer id, Model model) {

        LOGGER.debug("Edit existing product ({},{})", id, model);

        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("productCategories", productCategoryService.findAll());
        model.addAttribute("isNew", false);
        return "product";
    }

    /**
     * Updates already existing {@code product} by new one if it is valid
     * and redirect to page with list of products
     *
     * @param product product to update
     * @param result result object contains information about validation
     * @param model model contains information for view rendering
     * @return view template name
     */
    @PostMapping(value = "/product/{id}")
    public final String updateProduct(@Valid Product product, BindingResult result, Model model) {

        LOGGER.debug("Update product ({}, {})", product, result);

        productValidator.validate(product, result);
        model.addAttribute("productCategories", productCategoryService.findAll());
        if (result.hasErrors()) {
            model.addAttribute(productCategoryService.findAll());
            return "product";
        } else {
            productService.update(product);
            return "redirect:/products";
        }
    }

    /**
     * Goto add Product page.
     *
     * @return view template name
     */
    @GetMapping(value = "/product")
    public final String gotoAddProductPage(Model model) {

        LOGGER.debug("Goto add product page ({})", model);

        Product product = new Product();
        model.addAttribute("isNew", true);
        model.addAttribute("product", product);
        model.addAttribute("productCategories", productCategoryService.findAll());
        return "product";
    }

    /**
     * Save new {@code product} if it is valid
     * and redirect to page with list of products
     *
     * @param product product for saving
     * @param result result object contains information about product validation
     * @param model model contains information for view rendering
     * @return view template name
     */
    @PostMapping(value = "/product")
    public final String addProduct(@Valid Product product, BindingResult result, Model model) {

        LOGGER.debug("Add new Product ({})", product);

        productValidator.validate(product, result);

        if (result.hasErrors()) {
            model.addAttribute("productCategories", productCategoryService.findAll());
            return "product";
        } else {
            productService.add(product);
            return "redirect:/products";
        }
    }

    /**
     * Delete product by id
     *
     * @param id product id to delete
     * @return redirect to products list page
     */
    @GetMapping(value = "/products/{id}")
    public final String deleteProduct(@PathVariable Integer id, Model model) {

        LOGGER.debug("Delete product by id ({}, {})", id, model);

        productService.delete(id);
        return "redirect:/products";
    }

    /**
     *Go to page with list of products in category sorted by price interval
     *
     * @param result result object contains information about filter validation
     * @param model model contains information for view rendering
     * @return view template name
     */
    @PostMapping(value = "/products/price-category-filter")
    public final String productsByPriceInterval(BigDecimal priceStart, BigDecimal priceEnd,
                                                Integer productCategoryId, BindingResult result, Model model) {

        LOGGER.debug("Find all product stubs from price interval in category ({}, {}, {})", priceStart, priceEnd,
                productCategoryId);

        model.addAttribute("productCategories", productCategoryService.findAll());
        model.addAttribute("location", "products");

        if (result.hasErrors()) {
            return "products";
        } else {
            model.addAttribute("products", productService.findProductStubsFromPriceIntervalInCategory(priceStart,
                    priceEnd, productCategoryId));
            return "products";
        }
    }
}
