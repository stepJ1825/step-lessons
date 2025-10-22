--a) Изменение таблицы (добавление столбца)
ALTER TABLE my_schema.users ADD COLUMN status user_status DEFAULT 'active';
--b) Переименование столбца
ALTER TABLE my_schema.users RENAME COLUMN username TO login;
--c) Изменение типа столбца
ALTER TABLE my_schema.users ALTER COLUMN email TYPE VARCHAR(255);
--d) Добавление ограничения (constraint)
ALTER TABLE my_schema.users ADD CONSTRAINT chk_email_not_empty CHECK (email <> '');
--e) Удаление ограничения
ALTER TABLE my_schema.users DROP CONSTRAINT chk_email_not_empty;
--f) Изменение имени таблицы
ALTER TABLE my_schema.users RENAME TO accounts;
--g) Изменение схемы таблицы
ALTER TABLE accounts SET SCHEMA public;
--h) Изменение последовательности
ALTER SEQUENCE user_id_seq RESTART WITH 200;
