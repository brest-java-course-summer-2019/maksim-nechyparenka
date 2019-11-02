package com.epam.brest.summer.courses2019.web_app.validator;

import com.epam.brest.summer.courses2019.model.ProductCategory;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ProductCategoryValidator implements Validator {

    public static final int PRODUCT_CATEGORY_NAME_MAX_SIZE = 255;

    @Override
    public boolean supports(Class<?> cClass) {
        return ProductCategory.class.equals(cClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "productCategoryName", "productCategoryName.empty");

        ProductCategory productCategory = (ProductCategory) target;

        if (StringUtils.hasLength(productCategory.getProductCategoryName())
                && productCategory.getProductCategoryName().length() > PRODUCT_CATEGORY_NAME_MAX_SIZE) {
            errors.rejectValue("productCategoryName", "productCategoryName.maxSize255");
        }
    }
}
