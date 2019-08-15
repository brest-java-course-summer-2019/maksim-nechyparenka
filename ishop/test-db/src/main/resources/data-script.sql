INSERT INTO productCategory (product_category_id, product_category_name)
VALUES (1, 'Cell phones & Accessories');
INSERT INTO productCategory (product_category_id, product_category_name)
VALUES (2, 'Clothing');
INSERT INTO productCategory (product_category_id, product_category_name)
VALUES (3, 'Computers, Tablets');

INSERT INTO product (product_id, product_name, product_category_id, product_receiptdate, product_quantity, product_price)
VALUES (1, 'Samsung galaxy s8 plus g955f', 1, '28.12.2018', '8', '350');
INSERT INTO product (product_id, product_name, product_category_id, product_receiptdate, product_quantity, product_price)
VALUES (2, 'Puma Retro Womens Short', 2, '08.11.2018', '5', '15');
INSERT INTO product (product_id, product_name, product_category_id, product_receiptdate, product_quantity, product_price)
VALUES (3, 'Lenovo ThinkPad X240', 3, '10.02.2019', '7', '550');

INSERT INTO customerCategory (customer_category_id, customer_category_name)
VALUES (1, 'Normal');
INSERT INTO customerCategory (customer_category_id, customer_category_name)
VALUES (2, 'Blacklist');

INSERT INTO customer (customer_id, first_name, last_name, registration_date, login, password, card_number, customer_category_id)
VALUES (1, 'Danila', 'Kozlovsky', '08-08-2018', 'login01', 'password01', '1234 5678 9012 0001', 1);
INSERT INTO customer (customer_id, first_name, last_name, registration_date, login, password, card_number, customer_category_id)
VALUES (2, 'Ivan', 'Ivanov', '08-06-2019', 'login02', 'password02', '1234 5678 9012 0002', 1);
INSERT INTO customer (customer_id, first_name, last_name, registration_date, login, password, card_number, customer_category_id)
VALUES (3, 'Ivan', 'Petrov', '08-05-2019', 'login03', 'password03', '1234 5678 9012 0003', 1);
INSERT INTO customer (customer_id, first_name, last_name, registration_date, login, password, card_number, customer_category_id)
VALUES (4, 'Petr', 'Petrov', '08-03-2019', 'login04', 'password04', '1234 5678 9012 0004', 2);