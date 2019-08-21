DROP TABLE IF EXISTS productCategory;

CREATE TABLE productCategory (
                    product_category_id INT NOT NULL AUTO_INCREMENT,
                    product_category_name VARCHAR(255) NOT NULL UNIQUE,
                    PRIMARY KEY (product_category_id)
);

DROP TABLE IF EXISTS product;

CREATE TABLE product (
                    product_id INT NOT NULL AUTO_INCREMENT,
                    product_name VARCHAR(40) NOT NULL,
                    product_category_id INT NOT NULL,
                    product_receiptdate DATE NOT NULL,
                    product_quantity INT NOT NULL,
                    product_price INT NOT NULL,
                    PRIMARY KEY (product_id),
                    FOREIGN KEY (product_category_id)
                    REFERENCES productCategory (product_category_id)
);

DROP TABLE IF EXISTS customerCategory;

CREATE TABLE customerCategory (
                    customer_category_id INT NOT NULL AUTO_INCREMENT,
                    customer_category_name VARCHAR(255) NOT NULL,
                    PRIMARY KEY (customer_category_id)
);

DROP TABLE IF EXISTS customer;

CREATE TABLE customer (
                    customer_id INT NOT NULL AUTO_INCREMENT,
                    first_name VARCHAR(40) NOT NULL,
                    last_name VARCHAR(40) NOT NULL,
                    registration_date DATE NOT NULL,
                    login VARCHAR(40) NOT NULL UNIQUE,
                    pass_word VARCHAR(40) NOT NULL UNIQUE,
                    card_number VARCHAR(40) NOT NULL UNIQUE,
                    customer_category_id INT NOT NULL,
                    PRIMARY KEY (customer_id),
                    FOREIGN KEY (customer_category_id)
                    REFERENCES customerCategory (customer_category_id)
);