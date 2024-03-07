DROP TABLE PRODUCT IF EXISTS;
DROP TABLE SELLER IF EXISTS;

CREATE TABLE SELLER (
    seller_id int auto_increment primary key,
    name varchar(255) not null
);


CREATE TABLE PRODUCT (
    product_id int auto_increment primary key,
    product_name varchar (255),
    seller_id int references SELLER(seller_id),
    price double
);