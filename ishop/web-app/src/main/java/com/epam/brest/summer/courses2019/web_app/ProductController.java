package com.epam.brest.summer.courses2019.web_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Product controller.
 */
@Controller
public class ProductController {

    /**
     * Goto Products list page.
     *
     * @param model model
     * @return view name
     */
    @GetMapping(value = "/products")
    public final String products(Model model) {
        return "products";
    }
}
