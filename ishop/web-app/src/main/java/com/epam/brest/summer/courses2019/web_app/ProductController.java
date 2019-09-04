package com.epam.brest.summer.courses2019.web_app;

import com.epam.brest.summer.courses2019.model.Product;
import com.epam.brest.summer.courses2019.service.ProductService;
import com.epam.brest.summer.courses2019.web_app.validators.ProductValidator;
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
     * Goto edit Product page.
     *
     * @return view name
     */

    @GetMapping(value = "/product/{id}")
    public final String gotoEditProductPage(@PathVariable Integer id, Model model) {

        LOGGER.debug("gotoEditProductPage({},{})", id, model);
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product";
    }

    /**
     * Update product into storage.
     *
     * @return view name
     */
    @PostMapping(value = "/product/{id}")
    public String updateProduct(@Valid Product product, BindingResult result) {

        LOGGER.debug("updateProduct({},{})", product, result);
        productValidator.validate(product, result);
        if(result.hasErrors()) {
            return "product";
        } else {
            this.productService.update(product);
            return "redirect:/products";
        }
    }
}
