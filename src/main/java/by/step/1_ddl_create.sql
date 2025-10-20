--a) Создание базы данных
CREATE DATABASE my_database;
--b) Создание схемы
CREATE SCHEMA my_schema;
--c) Создание таблицы
CREATE TABLE public.users2 (id SERIAL PRIMARY KEY,username VARCHAR(50) NOT NULL UNIQUE,email TEXT CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);
--d) Создание индекса
CREATE INDEX idx_users_username ON my_schema.users (username);

--e) Создание представления (VIEW)
CREATE VIEW my_schema.active_users AS
SELECT id, username, email
FROM my_schema.users
WHERE created_at > NOW() - INTERVAL '30 days';

--f) Создание домена (пользовательский тип данных)
CREATE DOMAIN email_type AS TEXT
CHECK (VALUE ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$');

--g) Создание последовательности
CREATE SEQUENCE user_id_seq START 100;

--h) Создание типа ENUM
CREATE TYPE user_status AS ENUM ('active', 'inactive', 'banned');

--i) Создание функции (часто относится к DDL, хотя и содержит логику)
CREATE FUNCTION get_user_count() RETURNS INTEGER AS $$
    SELECT COUNT(*) FROM my_schema.users;
$$ LANGUAGE SQL;