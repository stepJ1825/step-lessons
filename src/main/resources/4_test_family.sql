-- 1. Создание таблиц
CREATE TABLE Goods
(
    good_id   INTEGER PRIMARY KEY,
    good_name TEXT NOT NULL,
    type      INTEGER
);

CREATE TABLE Payments
(
    payment_id    INTEGER PRIMARY KEY,
    date          TIMESTAMP,
    family_member INTEGER,
    good          INTEGER REFERENCES Goods (good_id),
    amount        INTEGER,
    unit_price    DECIMAL(10, 2)
);

-- 2. Вставка данных в Goods (включая икру)
INSERT INTO Goods (good_id, good_name, type)
VALUES (1, 'apartment fee', 1),
       (2, 'red caviar', 2),
       (3, 'black caviar', 2),
       (4, 'bread', 3),
       (5, 'milk', 3);

-- 3. Вставка данных в Payments
INSERT INTO Payments (payment_id, date, family_member, good, amount, unit_price)
VALUES
    -- Красная икра
    (101, '2020-01-10', 1, 2, 2, 4500.00), -- 2 шт по 4500
    (102, '2020-02-15', 2, 2, 1, 4700.00), -- 1 шт по 4700
    -- Чёрная икра
    (103, '2020-03-01', 1, 3, 3, 9000.00), -- 3 шт по 9000
    (104, '2020-04-20', 3, 3, 2, 9200.00), -- 2 шт по 9200
    -- Не икра — игнорируется
    (105, '2020-05-05', 2, 4, 5, 50.00),   -- хлеб
    (106, '2020-06-12', 1, 1, 1, 2000.00); -- коммуналка