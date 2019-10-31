package com.epam.brest.summer.courses2019.web_app.consumer;

import com.epam.brest.summer.courses2019.model.Product;
import com.epam.brest.summer.courses2019.model.stub.ProductStub;
import com.epam.brest.summer.courses2019.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.List;

public class ProductRestConsumer implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestConsumer.class);

    private final String url;

    private final RestTemplate restTemplate;

    public ProductRestConsumer(String url, RestTemplate restTemplate) {

        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> findAll() {

        LOGGER.debug("Find all products");

        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<Product>) responseEntity.getBody();
    }

    @Override
    public Product findById(Integer productId) {

        LOGGER.debug("Find product with id = {}", productId);

        ResponseEntity<Product> responseEntity = restTemplate.getForEntity(url + "/" + productId, Product.class);
        return responseEntity.getBody();
    }

    @Override
    public List<ProductStub> findAllStubs() {

        LOGGER.debug("Find all ProductStubs");

        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url + "/info", List.class);
        return (List<ProductStub>) responseEntity.getBody();
    }

    @Override
    public ProductStub findStubById(Integer productId) {

        LOGGER.debug("Find productStub with id = {}", productId);

        ResponseEntity<ProductStub> responseEntity = restTemplate
                .getForEntity(url + "/" + productId, ProductStub.class);
        return responseEntity.getBody();
    }

    @Override
    public List<Product> findByProductCategoryId(Integer categoryId) {

        LOGGER.debug("Find product by category id = {}", categoryId);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url + "/category-filter" + "/" + categoryId)
                .queryParam("id", categoryId);

        ResponseEntity<List> responseEntity = restTemplate.getForEntity(uriBuilder.toUriString(), List.class);

        return (List<Product>) responseEntity.getBody();
    }

    @Override
    public List<ProductStub> findStubsByProductCategoryId(Integer categoryId) {

        LOGGER.debug("Find productStubs by category id = {}", categoryId);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url + "/category-filter" + "/" + categoryId)
                .queryParam("id", categoryId);

        ResponseEntity<List> responseEntity = restTemplate.getForEntity(uriBuilder.toUriString(), List.class);

        return (List<ProductStub>) responseEntity.getBody();
    }

    @Override
    public List<ProductStub> findProductStubsFromPriceIntervalInCategory(BigDecimal priceStart, BigDecimal priceEnd,
                                                                  Integer productCategoryId) {

        LOGGER.debug("Find productStubs from price interval by category id = {}, {}, {}",
                priceStart, priceEnd, productCategoryId);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url + "/price-category-filter" + "/" + priceStart + priceEnd + productCategoryId)
                .queryParam("priceStart", priceStart)
                .queryParam("priceEnd", priceEnd)
                .queryParam("productCategoryId", productCategoryId);

        ResponseEntity<List> responseEntity = restTemplate.getForEntity(uriBuilder.toUriString(), List.class);

        return (List<ProductStub>) responseEntity.getBody();
    }

    @Override
    public Product add(Product product) {

        LOGGER.debug("Add new product ({})", product);
        return restTemplate.postForEntity(url, product, Product.class).getBody();
    }

    @Override
    public void update(Product product) {

        LOGGER.debug("Update existing product ({})", product);

        restTemplate.put(url + "/" + product.getProductId(), product);
    }

    @Override
    public void delete(Integer productId) {

        LOGGER.debug("Delete product with id = {}", productId);

        restTemplate.delete(url + "/" + productId);
    }
}
