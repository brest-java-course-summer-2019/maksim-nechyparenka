package com.epam.brest.summer.courses2019.web_app.consumer;

import com.epam.brest.summer.courses2019.model.Product;
import com.epam.brest.summer.courses2019.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
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
