-- 1. Создание таблиц
CREATE TABLE users1
(
    id   SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE orders1
(
    id      SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users1 (id),
    amount  NUMERIC(10, 2)
);

-- 2. Наполнение данными
INSERT INTO users1 (name)
VALUES ('Alice'),
       ('Bob'),
       ('Charlie'),
       ('Diana');

INSERT INTO orders1 (user_id, amount)
VALUES (1, 150.00), -- заказ от Alice
       (1, 75.50),  -- ещё один заказ от Alice
       (3, 200.00), -- заказ от Charlie
       (3, 90.00);
-- ещё один заказ от Charlie
-- у Bob (id=2) и Diana (id=4) нет заказов