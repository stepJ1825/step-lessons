--1. Подготовка данных
--Создадим тестовую таблицу:
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(255),
    created_at TIMESTAMP DEFAULT NOW()
);
-- Заполним таблицу большим количеством данных
INSERT INTO users (name, email)
SELECT
    'User ' || g,
    'user' || g || '@example.com'
FROM generate_series(1, 100000) AS g;

--2. Выполняем запрос без индекса
EXPLAIN ANALYZE
SELECT * FROM users WHERE email = 'user50000@example.com';

--3. Создаём индекс
CREATE INDEX idx_users_email ON users(email);

--4. Повторяем запрос с индексом
EXPLAIN ANALYZE
SELECT * FROM users WHERE email = 'user50000@example.com';