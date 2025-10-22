--COLLATE — задание правил сортировки и сравнения текста
--COLLATE указывает локаль (collation) для строковых операций (например, сортировка, сравнение). Может использоваться:
--  при создании таблицы,
--  в выражениях ORDER BY, WHERE,
--  при создании индексов.

CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name TEXT COLLATE "C"  -- чувствительная к регистру сортировка
);
--"C" — это простая байтовая сортировка (case-sensitive).
--"en_US.UTF-8" или "ru_RU.UTF-8" — локали с учётом языка (case-insensitive в некоторых контекстах).

--Пример: использование COLLATE в запросе
SELECT * FROM products
ORDER BY name COLLATE "ru_RU.UTF-8";

--Пример: индекс с COLLATE
CREATE INDEX idx_product_name_ru ON products (name COLLATE "ru_RU.UTF-8");