create sequence id_for_customers start 1 increment 1;
create sequence id_for_orders start 1 increment 1;
create sequence id_for_products start 1 increment 1;
create sequence id_for_order_products start 1 increment 1;
create sequence id_for_delivery_list start 1 increment 1;

create table customers (
                           customer_id INTEGER PRIMARY KEY DEFAULT nextval('id_for_customers'),
                           first_name VARCHAR NOT NULL,
                           last_name VARCHAR NOT NULL,
                           phone_number VARCHAR NOT NULL,
                           city VARCHAR NOT NULL,
                           street VARCHAR NOT NULL,
                           house INTEGER NOT NULL,
                           appartament INTEGER NOT NULL,
                           username VARCHAR NOT NULL,
                           password VARCHAR NOT NULL,
                           role VARCHAR NOT NULL
);

create table orders (
                        order_id INTEGER PRIMARY KEY DEFAULT nextval('id_for_orders'),
                        customer_id INTEGER NOT NULL,
                        date_get DATE,
                        FOREIGN KEY (customer_id) REFERENCES customers (customer_id)
);

create table products (
                          product_id INTEGER PRIMARY KEY DEFAULT nextval('id_for_products'),
                          product_name VARCHAR NOT NULL,
                          description VARCHAR NOT NULL,
                          price NUMERIC NOT NULL
);

create table order_products (
                                order_product_id INTEGER PRIMARY KEY DEFAULT nextval('id_for_order_products'),
                                product_id INTEGER NOT NULL,
                                order_id INTEGER NOT NULL,
                                FOREIGN KEY (product_id) REFERENCES products (product_id),
                                FOREIGN KEY (order_id) REFERENCES orders (order_id)
);

create table delivery_list (
                               delivery_id INTEGER PRIMARY KEY DEFAULT nextval('id_for_delivery_list'),
                               date_arrived DATE NOT NULL,
                               payment_method VARCHAR NOT NULL,
                               order_id INTEGER NOT NULL,
                               FOREIGN KEY (order_id) REFERENCES orders (order_id)
)