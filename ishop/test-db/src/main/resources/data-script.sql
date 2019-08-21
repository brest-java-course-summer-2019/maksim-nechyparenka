INSERT INTO productCategory (product_category_id, product_category_name)
VALUES (1, 'Cell phones & Accessories');
INSERT INTO productCategory (product_category_id, product_category_name)
VALUES (2, 'Clothing');
INSERT INTO productCategory (product_category_id, product_category_name)
VALUES (3, 'Computers, Tablets');

INSERT INTO product (product_id, product_name, product_category_id, product_receiptdate, product_quantity, product_price)
VALUES (1, 'Samsung galaxy s8 plus g955f', 1, '2018-12-28', '8', '350');
INSERT INTO product (product_id, product_name, product_category_id, product_receiptdate, product_quantity, product_price)
VALUES (2, 'Puma Retro Womens Short', 2, '2018-11-08', '5', '15');
INSERT INTO product (product_id, product_name, product_category_id, product_receiptdate, product_quantity, product_price)
VALUES (3, 'Lenovo ThinkPad X240', 3, '2019-02-10', '7', '550');

INSERT INTO customerCategory (customer_category_id, customer_category_name)
VALUES (1, 'Normal');
INSERT INTO customerCategory (customer_category_id, customer_category_name)
VALUES (2, 'Blacklist');

INSERT INTO customer (customer_id, first_name, last_name, registration_date, login, pass_word, card_number, customer_category_id)
VALUES (1, 'Danila', 'Kozlovsky', '2018-08-08', 'login01', 'password01', '1234 5678 9012 0001', 1);
INSERT INTO customer (customer_id, first_name, last_name, registration_date, login, pass_word, card_number, customer_category_id)
VALUES (2, 'Ivan', 'Ivanov', '2019-06-08', 'login02', 'password02', '1234 5678 9012 0002', 1);
INSERT INTO customer (customer_id, first_name, last_name, registration_date, login, pass_word, card_number, customer_category_id)
VALUES (3, 'Ivan', 'Petrov', '2019-05-08', 'login03', 'password03', '1234 5678 9012 0003', 1);
INSERT INTO customer (customer_id, first_name, last_name, registration_date, login, pass_word, card_number, customer_category_id)
VALUES (4, 'Petr', 'Petrov', '2019-03-08', 'login04', 'password04', '1234 5678 9012 0004', 2);