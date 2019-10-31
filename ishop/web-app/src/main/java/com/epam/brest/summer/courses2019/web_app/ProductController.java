package com.epam.brest.summer.courses2019.web_app;

import com.epam.brest.summer.courses2019.model.Product;
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

/**
 * Product controller.
 */
@Controller
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    ProductValidator productValidator;

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

        return "products";
    }

    /**
     * Goto edit existing Product page by Id.
     *
     * @return view name
     */
    @GetMapping(value = "/products/{id}")
    public final String gotoEditExistingProductPage(@PathVariable Integer id, Model model) {

        LOGGER.debug("Edit existing product({},{})", id, model);

        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product";
    }

    /**
     * Update existing Product in the catalogue.
     *
     * @return view name
     */
    @PostMapping(value = "/product/{id}")
    public String updateExistingProduct(@Valid Product product, BindingResult result) {

        LOGGER.debug("Update existing product({},{})", product, result);

        productValidator.validate(product, result);
        if(result.hasErrors()) {
            return "product";
        } else {
            this.productService.update(product);
            return "redirect:/products";
        }
    }

    /**
     * Goto add Product page.
     *
     * @return view name
     */
    @GetMapping(value = "/product")
    public final String gotoAddProductPage(Model model) {

        LOGGER.debug("Goto add product page({})", model);

        Product product = new Product();
        model.addAttribute("isNew", true);
        model.addAttribute("product", product);
        return "product";
    }
}
