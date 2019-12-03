package com.epam.brest.summer.courses2019.web_app.consumer;

import com.epam.brest.summer.courses2019.model.ProductCategory;
import com.epam.brest.summer.courses2019.service.ProductCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ProductCategoryRestConsumer implements ProductCategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCategoryRestConsumer.class);

    private final String url;

    private final RestTemplate restTemplate;

    public ProductCategoryRestConsumer(String url, RestTemplate restTemplate) {

        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ProductCategory> findAll() {

        LOGGER.debug("Find all product categories");

        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url + "/", List.class);
        return (List<ProductCategory>) responseEntity.getBody();
    }

    @Override
    public ProductCategory findProductCategoryById(Integer productCategoryId) {

        LOGGER.debug("Find product category with id = {}", productCategoryId);

        ResponseEntity<ProductCategory> responseEntity = restTemplate.getForEntity(url + "/"
                + productCategoryId, ProductCategory.class);
        return responseEntity.getBody();
    }

    @Override
    public ProductCategory add(ProductCategory product) {

        LOGGER.debug("Add new product category ({})", product);
        return restTemplate.postForEntity(url, product, ProductCategory.class).getBody();
    }

    @Override
    public void update(ProductCategory productCategory) {

        LOGGER.debug("Update existing product category ({})", productCategory);

        restTemplate.put(url + "/" + productCategory.getProductCategoryId(), productCategory);
    }

    @Override
    public void delete(Integer productCategoryId) {

        LOGGER.debug("Delete product category with id = {}", productCategoryId);

        restTemplate.delete(url + "/" + productCategoryId);
    }
}
