package com.epam.brest.summer.courses2019.rest_app;

import com.epam.brest.summer.courses2019.model.Product;
import com.epam.brest.summer.courses2019.model.stub.ProductStub;
import com.epam.brest.summer.courses2019.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductRestController {

    /**
      * Logger for current class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);

    /**
     * ProductService object to get information of products
     */
    private ProductService productService;

    /**
     * Constructor with ProductService argument
     *
     * @param productService product service object
     */
    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Returns all {@code products} from DataBase
     *
     * @return {@code List} of a {@code products}
     */
    @GetMapping(value = "/admin")
    public List<Product> findAll() {

        LOGGER.debug("Get all products from DataBase");

        return productService.findAll();
    }

    /**
     * Returns a {@code product} with specified id
     *
     * @param productId id of required {@code product}
     * @return required {@code product}
     */
    @GetMapping(value = "/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Product findById(@PathVariable("productId") Integer productId) {

        LOGGER.debug("Get product by id = {}", productId);

        return productService.findById(productId);
    }

    /**
     * Returns all {@code product stubs} from DataBase
     *
     * @return {@code List} of a {@code product stubs Objects}
     */
    @GetMapping(value = "/info")
    public List<ProductStub> findAllProductStubs() {

        LOGGER.debug("Get all productStubs");

        return productService.findAllStubs();
    }

    /**
     * Returns a {@code productStub} with specified id
     *
     * @param productId id of required {@code productStub}
     * @return required {@code productStub}
     */
    @GetMapping(value = "/info/{productId}")
    public ProductStub findStubById(@PathVariable("productId") Integer productId) {

        LOGGER.debug("Get product by id = {}", productId);

        return productService.findStubById(productId);
    }

    /**
     * Returns all {@code productStubs Objects} that
     * matches given request params
     *
     * @param categoryId Category id to select productStubs by
     * @return {@code List} of a {@code productStub Objects}
     */
    @GetMapping(value = "/category-filter/{categoryId}")
    public List<ProductStub> findProductStubsInCategory(@PathVariable(value = "categoryId", required = false)
                                                                    Integer categoryId) {

        LOGGER.debug("Find ProductStubs in category ({})", categoryId);

        return productService.findStubsByProductCategoryId(categoryId);
    }

    /**
     * Returns all {@code product Data Transfer Objects} that
     * matches given request params
     *
     * @param priceStart Beginning of price interval
     * @param priceEnd Ending of price interval
     * @param categoryId Category id to select product Stubs by
     * @return {@code List} of a {@code product Stub Objects}
     */
    @GetMapping(value = "/price-category-filter/{priceStart}-{priceEnd}-{categoryId}")
    public List<ProductStub> findProductStubsFromPriceIntervalInCategory(

            @PathVariable(value = "priceStart", required = false) BigDecimal priceStart,
            @PathVariable(value = "priceEnd", required = false) BigDecimal priceEnd,
            @PathVariable(value = "categoryId", required = false) Integer categoryId) {

        LOGGER.debug("Find ProductStubs from price interval in category ({},{},{})", priceStart, priceEnd, categoryId);

        return productService.findProductStubsFromPriceIntervalInCategory(priceStart, priceEnd, categoryId);
    }

    /**
     * Saves {@code product} to a DataBase
     *
     * @param product {@code product} to be saved
     * @return just saved {@code product} with given id
     */
    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product) {

        LOGGER.debug("Add product ({})", product);

        return productService.add(product);
    }

    /**
     * Updates already existing {@code product} by new one
     *
     * @param product {@code product} to update older one
     */
    @PutMapping(value = "/products")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateProduct(@RequestBody Product product) {

        LOGGER.debug("Update existing product ({})", product);

        productService.update(product);
    }

    /**
     * Deletes existing {@code product} with specified product id
     *
     * @param productId product id to delete {@code product}
     */
    @DeleteMapping(value = "/{productId}")
    public void deleteProduct(@PathVariable Integer productId) {

        LOGGER.debug("Delete product ({})", productId);

        productService.delete(productId);
    }
}
