DROP TABLE IF EXISTS product;

CREATE TABLE product (
                     product_id INT NOT NULL AUTO_INCREMENT,
                     product_name VARCHAR(40) NULL ,
                     product_category_id VARCHAR(40) NULL,
                     product_receiptdate VARCHAR(10) NULL,
                     product_quantity VARCHAR(40) NULL,
                     product_price VARCHAR(40) NULL,
                     PRIMARY KEY (product_id)
                     FOREIGN KEY (product_category_id) REFERENCES productCategory(product_category_id)
                     );

DROP TABLE IF EXISTS productCategory;

CREATE TABLE productCategory (
                    product_category_id INT NOT NULL AUTO_INCREMENT,
                    product_category_name VARCHAR(255) NULL ,
                    PRIMARY KEY (product_category_id)
                    );

DROP TABLE IF EXISTS customer;

CREATE TABLE customer (
                    customer_id INT NOT NULL AUTO_INCREMENT,
                    first_name VARCHAR(40) NULL ,
                    last_name VARCHAR(40) NULL,
                    registration_date VARCHAR(10) NULL,
                    login VARCHAR(40) NULL,
                    password VARCHAR(40) NULL,
                    card_number VARCHAR(40) NULL,
                    customer_category_id VARCHAR(40) NULL,
                    PRIMARY KEY (customer_id)
                    FOREIGN KEY (customer_category_id) REFERENCES customerCategory(customer_category_id)
                    );

DROP TABLE IF EXISTS customerCategory;

CREATE TABLE customerCategory (
                    customer_category_id INT NOT NULL AUTO_INCREMENT,
                    customer_category_name VARCHAR(255) NULL ,
                    PRIMARY KEY (customer_category_id)
                    );