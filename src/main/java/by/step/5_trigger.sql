--Триггеры в PostgreSQL требуют функции, которая будет вызываться при наступлении события.

--Шаг 1: Создать функцию-обработчик триггера
CREATE OR REPLACE FUNCTION log_user_update()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO user_audit_log (user_id, old_email, new_email, changed_at)
    VALUES (OLD.id, OLD.email, NEW.email, NOW());
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

--Шаг 2: Создать таблицу для логирования (если нужно)
CREATE TABLE user_audit_log (
    id SERIAL PRIMARY KEY,
    user_id INT,
    old_email TEXT,
    new_email TEXT,
    changed_at TIMESTAMP
);

--Шаг 3: Создать триггер
CREATE TRIGGER trigger_log_email_change
    BEFORE UPDATE OF email ON users
    FOR EACH ROW
    WHEN (OLD.email IS DISTINCT FROM NEW.email)
    EXECUTE FUNCTION log_user_update();


--Обратите внимание:
--BEFORE / AFTER / INSTEAD OF
--FOR EACH ROW или FOR EACH STATEMENT
--WHEN — условие срабатывания
--EXECUTE FUNCTION (в новых версиях PostgreSQL — EXECUTE FUNCTION, а не EXECUTE PROCEDURE)


--Удаление триггера
DROP TRIGGER IF EXISTS trigger_log_email_change ON users;

---------------------------------------
--Полный пример: всё вместе
-- Создаём таблицу с COLLATE
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username TEXT COLLATE "C" NOT NULL,
    email TEXT
);

-- Переименовываем столбец
ALTER TABLE users RENAME COLUMN username TO login;

-- Таблица для аудита
CREATE TABLE user_audit_log (
    id SERIAL,
    user_id INT,
    old_email TEXT,
    new_email TEXT,
    changed_at TIMESTAMP
);

-- Функция триггера
CREATE OR REPLACE FUNCTION log_email_change()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO user_audit_log (user_id, old_email, new_email, changed_at)
    VALUES (OLD.id, OLD.email, NEW.email, NOW());
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Триггер
CREATE TRIGGER tr_email_update
    BEFORE UPDATE OF email ON users
    FOR EACH ROW
    WHEN (OLD.email IS DISTINCT FROM NEW.email)
    EXECUTE FUNCTION log_email_change();