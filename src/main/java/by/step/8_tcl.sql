--TCL (Transaction Control Language)
--💡 В PostgreSQL TCL включает команды для управления транзакциями:
--BEGIN / START TRANSACTION, COMMIT, ROLLBACK, SAVEPOINT, RELEASE SAVEPOINT, ROLLBACK TO SAVEPOINT.
--Также важны параметры уровня изоляции и режимы транзакций.

-- =============================================================================
-- Файл: tcl_examples.sql
-- Описание: Примеры всех основных TCL-команд в PostgreSQL
-- TCL = Transaction Control Language
-- Примечание: Для демонстрации используется тестовая таблица.
-- =============================================================================

-- 1. Подготовка: создание таблицы
CREATE TABLE IF NOT EXISTS accounts (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL UNIQUE,
    balance NUMERIC(12, 2) NOT NULL CHECK (balance >= 0)
);

-- Очистка данных
TRUNCATE accounts RESTART IDENTITY;

-- Вставка начальных данных
INSERT INTO accounts (name, balance)
VALUES
    ('Alice', 1000.00),
    ('Bob', 500.00);

-- =============================================================================
-- 2. Простая транзакция: BEGIN ... COMMIT
-- =============================================================================

-- Начало транзакции
BEGIN;

-- Выполнение операций (перевод денег)
UPDATE accounts SET balance = balance - 200.00 WHERE name = 'Alice';
UPDATE accounts SET balance = balance + 200.00 WHERE name = 'Bob';

-- Фиксация изменений
COMMIT;

-- Проверка результата
SELECT * FROM accounts;

-- =============================================================================
-- 3. Откат транзакции: BEGIN ... ROLLBACK
-- =============================================================================

BEGIN;

-- Попытка перевода
UPDATE accounts SET balance = balance - 100.00 WHERE name = 'Alice';
UPDATE accounts SET balance = balance + 100.00 WHERE name = 'Bob';

-- Но передумали — откатываем всё
ROLLBACK;

-- Результат: балансы остались как до транзакции
SELECT * FROM accounts;

-- =============================================================================
-- 4. Транзакция с точками сохранения (SAVEPOINT)
-- =============================================================================

BEGIN;

-- Шаг 1: снимаем деньги с Alice
UPDATE accounts SET balance = balance - 300.00 WHERE name = 'Alice';
SAVEPOINT sp1;

-- Шаг 2: начисляем Bob'у
UPDATE accounts SET balance = balance + 150.00 WHERE name = 'Bob';
SAVEPOINT sp2;

-- Шаг 3: ошибка! Нужно откатиться к sp1
ROLLBACK TO SAVEPOINT sp1;

-- Теперь отменяем только действия после sp1 (возврат Bob'у отменён),
-- но списание с Alice остаётся

-- Продолжаем транзакцию: начисляем другому пользователю
INSERT INTO accounts (name, balance) VALUES ('Charlie', 300.00);

-- Фиксируем всё, что осталось после отката к sp1
COMMIT;

-- Проверка итогового состояния
SELECT * FROM accounts ORDER BY name;

-- =============================================================================
-- 5. Удаление точки сохранения (необязательно, но возможно)
-- =============================================================================

BEGIN;

UPDATE accounts SET balance = balance + 50.00 WHERE name = 'Alice';
SAVEPOINT temp_save;

-- Делаем что-то...
UPDATE accounts SET balance = balance - 50.00 WHERE name = 'Charlie';

-- Удаляем точку сохранения (освобождаем ресурсы)
RELEASE SAVEPOINT temp_save;

-- Фиксируем
COMMIT;

-- =============================================================================
-- 6. Явное указание уровня изоляции транзакции
-- =============================================================================

-- Пример: сериализуемая транзакция (максимальная изоляция)
BEGIN ISOLATION LEVEL SERIALIZABLE;

-- Чтение данных
SELECT balance FROM accounts WHERE name = 'Alice';

-- Выполнение операций
UPDATE accounts SET balance = balance + 10.00 WHERE name = 'Alice';

-- Фиксация
COMMIT;

-- Другие допустимые уровни:
--   READ UNCOMMITTED → в PG ведёт себя как READ COMMITTED
--   READ COMMITTED     (уровень по умолчанию)
--   REPEATABLE READ
--   SERIALIZABLE

-- =============================================================================
-- 7. Режимы транзакций: READ ONLY / READ WRITE
-- =============================================================================

-- Транзакция только для чтения (запрещает любые изменения)
BEGIN READ ONLY;

-- Это сработает:
SELECT * FROM accounts;

-- Это вызовет ошибку (если раскомментировать):
-- UPDATE accounts SET balance = 0;  -- ERROR: cannot execute UPDATE in a read-only transaction

COMMIT;

-- =============================================================================
-- 8. Автоматические транзакции (по умолчанию)
-- =============================================================================

-- В PostgreSQL каждый SQL-запрос вне явной транзакции выполняется
-- в своей собственной транзакции (авто-commit).
-- Пример:
UPDATE accounts SET balance = balance + 1.00 WHERE name = 'Alice';
-- Эта команда автоматически завершается COMMIT'ом.

-- =============================================================================
-- Завершение: очистка
-- =============================================================================
DROP TABLE IF EXISTS accounts;




--Ключевые особенности TCL в PostgreSQL:
--BEGIN или START TRANSACTION - Начало транзакции
--COMMIT - Фиксация всех изменений
--ROLLBACK - Отмена всех изменений с начала транзакции
--SAVEPOINT имя - Создание точки сохранения внутри транзакции
--ROLLBACK TO SAVEPOINT имя - Откат до точки сохранения
--RELEASE SAVEPOINT имя - Удаление точки сохранения (опционально)
--ISOLATION LEVEL ...  - Установка уровня изоляции
--READ ONLY/READ WRITE - Ограничение на запись
--⚠️ Важно:
--В PostgreSQL нет команды AUTOCOMMIT как в некоторых других СУБД — режим автокоммита управляется на уровне клиента (например, в psql или драйверах).
--Все изменения вне явной транзакции автоматически коммитятся по завершении запроса.


--Как выполнить:
--psql -U ваш_пользователь -d ваша_база -f tcl_examples.sql