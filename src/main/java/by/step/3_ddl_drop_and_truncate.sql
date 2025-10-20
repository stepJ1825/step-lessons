--a) Удаление таблицы
DROP TABLE IF EXISTS public.accounts CASCADE;
--b) Удаление схемы
DROP SCHEMA IF EXISTS my_schema CASCADE;
--c) Удаление базы данных
DROP DATABASE IF EXISTS my_database;
-- (выполняется не внутри самой БД, а из другой сессии)
--d) Удаление индекса
DROP INDEX IF EXISTS idx_users_username;
--e) Удаление представления
DROP VIEW IF EXISTS active_users;
--f) Удаление домена
DROP DOMAIN IF EXISTS email_type;
--g) Удаление типа ENUM
DROP TYPE IF EXISTS user_status;
--h) Удаление функции
DROP FUNCTION IF EXISTS get_user_count();

--TRUNCATE — удаление всех данных из таблицы (иногда относят к DDL)
TRUNCATE TABLE public.accounts RESTART IDENTITY CASCADE;
--Примечание: TRUNCATE удаляет все строки из таблицы и сбрасывает связанные последовательности (RESTART IDENTITY).
--Это быстрее, чем DELETE FROM.