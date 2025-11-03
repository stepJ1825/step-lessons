DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA public;


DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    price       float4       NOT NULL
);

DROP TABLE IF EXISTS orders CASCADE;
CREATE TABLE orders
(
    id         SERIAL PRIMARY KEY,
    --order_ID   VARCHAR(255) NOT NULL,
    order_date TIMESTAMP    NOT NULL
);
DROP TABLE IF EXISTS order_items CASCADE;
CREATE TABLE order_items
(
    id         SERIAL PRIMARY KEY,
    order_id   INTEGER REFERENCES orders (id),
    product_id INTEGER REFERENCES products (id),
    quantity   INTEGER NOT NULL
);


INSERT INTO products (name, description, price)
VALUES ('Personal Computer', 'System Block', 2341),
       ('mouse', '2400 dpi', 30),
       ('Monitor', '27"', 600);

INSERT INTO orders (order_date)
VALUES ('20.12.2015');

INSERT INTO order_items (order_id, product_id, quantity)
VALUES (1, select id from products where name = 'Personal Computer', 2),
       (1,2,1),
       (1,3, 2);


SELECT
    o.id as order_id,
    o.order_date,
    p.name as product_name,
    p.description,
    p.price,
    oi.quantity,
    (p.price * oi.quantity) as total_price
FROM orders o
         JOIN order_items oi ON o.id = oi.order_id
         JOIN products p ON oi.product_id = p.id
WHERE o.id = 1;



--1.Нужны ещё классы из предыдущей домашки, чтобы лучше понять предметную область.
--2.Странная таблица orders, которая содержит только дату заказа.
--3.Столбец order_ID закомментирован. Зачем он вообще?
--4.3+1+3. Маловато тестовых данных. Сделай чуть больше.
