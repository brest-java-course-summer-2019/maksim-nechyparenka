DROP TABLE IF EXISTS productCategory;

CREATE TABLE productCategory (
                    product_category_id INT NOT NULL AUTO_INCREMENT,
                    product_category_name VARCHAR(255) NOT NULL UNIQUE,
                    PRIMARY KEY (product_category_id),
);

DROP TABLE IF EXISTS product;

CREATE TABLE product (
                    product_id INT NOT NULL AUTO_INCREMENT,
                    product_name VARCHAR(255) NOT NULL,
                    product_category_id INT NOT NULL,
                    product_supplier_name VARCHAR(255) NOT NULL,
                    product_receiptdate DATE NOT NULL,
                    product_quantity DECIMAL(15,2) NOT NULL,
                    product_price DECIMAL(15,2) NOT NULL,
                    PRIMARY KEY (product_id),
                    FOREIGN KEY (product_category_id) REFERENCES productCategory (product_category_id)
);
