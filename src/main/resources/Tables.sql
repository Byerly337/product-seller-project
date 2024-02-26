DROP TABLE SELLER IF EXISTS;
CREATE TABLE SELLER(
    seller_id int primary key,
    name varchar(255) not null
);

CREATE TABLE PRODUCT (
    product_id int primary key,
    product_name varchar(255) not null,
    price int,
    seller_name int references SELLER(seller_id)
);
INSERT INTO SELLER (seller_id, name)
VALUES (?,?)
;
INSERT INTO PRODUCT (product_id, product_name, price, seller_name)
VALUES (?,?,?,?);