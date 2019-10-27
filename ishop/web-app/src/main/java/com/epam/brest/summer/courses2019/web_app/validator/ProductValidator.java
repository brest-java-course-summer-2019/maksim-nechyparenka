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
    public static final int PRODUCT_SUPPLIER_NAME_MAX_SIZE = 255;

    @Override
    public boolean supports(Class<?> pClass) {
        return Product.class.equals(pClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "productName", "productName.empty");
        ValidationUtils.rejectIfEmpty(errors, "productSupplierName", "productSupplierName.empty");
        Product product = (Product) target;

        if (StringUtils.hasLength(product.getProductName())
                && product.getProductName().length() > PRODUCT_NAME_MAX_SIZE) {
            errors.rejectValue("productName", "productName.maxSize255");
        }

        if (StringUtils.hasLength(product.getProductSupplierName())
                && product.getProductName().length() > PRODUCT_SUPPLIER_NAME_MAX_SIZE) {
            errors.rejectValue("productSupplierName", "productSupplierName.maxSize255");
        }

        if (product.getProductQuantity() == null) {
            errors.rejectValue("productQuantity", "product.emptyQuantity");
        } else {
            if (product.getProductQuantity().doubleValue() < 0) {
                errors.rejectValue("productQuantity", "product.negativeQuantity");
            }
        }
    }

}
