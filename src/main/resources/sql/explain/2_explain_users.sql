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
FROM generate_series((select coalesce(max(id),1)+1 from users),
                     (select coalesce(max(id),1)+1_000_000 from users)
					) AS g;
--вставка прошла за 7.0 секунд
--вставка с наличием индекса за 14 секунд
--вставка после удаления индекса прошла за 7.0 секунд
--вставка с тремя индексами за 32 секунды

--2. Выполняем запрос без индекса
EXPLAIN (ANALYZE, BUFFERS)
SELECT * FROM users WHERE email = 'user50000@example.com';

--3. Создаём индекс/индексы
CREATE INDEX CONCURRENTLY idx_users_email ON users(email);
CREATE INDEX idx_users_name ON users("name");
CREATE INDEX idx_users_created_at ON users(created_at);
-- создание индекса за 6.3 секунды
-- создание индекса за 5.1 секунды c CONCURRENTLY

--4. Повторяем запрос с индексом
EXPLAIN ANALYZE
SELECT * FROM users WHERE email = 'user50000@example.com';