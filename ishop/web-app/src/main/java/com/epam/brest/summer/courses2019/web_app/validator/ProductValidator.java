package com.epam.brest.summer.courses2019.web_app.validator;

import com.epam.brest.summer.courses2019.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {

    public static final int PRODUCT_NAME_MAX_SIZE = 255;

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "productName", "productName.empty");
        Product product = (Product) target;

        if (StringUtils.hasLength(product.getProductName())
                && product.getProductName().length() > PRODUCT_NAME_MAX_SIZE) {
            errors.rejectValue("productName", "productName.maxSize255");
        }
    }

}
