-- =============================================================================
-- Файл: dml_examples.sql
-- Описание: Примеры всех основных DML-команд в PostgreSQL
-- DML = Data Manipulation Language: SELECT, INSERT, UPDATE, DELETE, MERGE (начиная с v15)
-- Примечание: Для корректной работы создадим тестовую таблицу и данные.
-- =============================================================================

-- 1. Подготовка: создание таблицы и вставка начальных данных (DDL + DML)
-- (Это не DML, но необходимо для демонстрации)

CREATE TABLE IF NOT EXISTS employees (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    department TEXT,
    salary NUMERIC(10, 2),
    hire_date DATE DEFAULT CURRENT_DATE
);

-- Очистим таблицу, если она уже содержит данные
TRUNCATE employees RESTART IDENTITY;

-- =============================================================================
-- 2. INSERT — вставка новых строк
-- =============================================================================

-- Простая вставка одной строки
INSERT INTO employees (name, department, salary)
VALUES ('Анна Петрова', 'Маркетинг', 75000.00);

-- Вставка нескольких строк за один запрос
INSERT INTO employees (name, department, salary)
VALUES
    ('Иван Сидоров', 'Разработка', 120000.00),
    ('Мария Козлова', 'Финансы', 95000.00),
    ('Алексей Иванов', 'Разработка', 110000.00);

-- Вставка с использованием DEFAULT для hire_date
INSERT INTO employees (name, department, salary, hire_date)
VALUES ('Елена Смирнова', 'HR', 80000.00, DEFAULT);

-- Вставка на основе SELECT (копирование данных)
INSERT INTO employees (name, department, salary)
SELECT name || ' (резерв)', department, salary * 0.9
FROM employees
WHERE department = 'Разработка';

-- =============================================================================
-- 3. SELECT — выборка данных
-- =============================================================================

-- Простой SELECT: все столбцы и строки
SELECT * FROM employees;

-- SELECT с указанием столбцов
SELECT name, department, salary FROM employees;

-- SELECT с фильтрацией (WHERE)
SELECT name, salary
FROM employees
WHERE salary > 90000;

-- SELECT с сортировкой (ORDER BY)
SELECT name, department, salary
FROM employees
ORDER BY salary DESC;

-- SELECT с ограничением количества строк (LIMIT)
SELECT name, hire_date
FROM employees
ORDER BY hire_date
LIMIT 2;

-- SELECT с агрегацией (GROUP BY + агрегатные функции)
SELECT department, COUNT(*) AS employee_count, AVG(salary) AS avg_salary
FROM employees
GROUP BY department;

-- SELECT с условием на группы (HAVING)
SELECT department, AVG(salary) AS avg_salary
FROM employees
GROUP BY department
HAVING AVG(salary) > 85000;

-- =============================================================================
-- 4. UPDATE — обновление существующих строк
-- =============================================================================

-- Обновление всех строк (осторожно!)
-- UPDATE employees SET salary = salary * 1.1;

-- Обновление с условием WHERE
UPDATE employees
SET salary = salary * 1.05
WHERE department = 'Маркетинг';

-- Обновление нескольких столбцов
UPDATE employees
SET department = 'Управление', salary = 130000.00
WHERE name = 'Иван Сидоров';

-- Обновление с использованием подзапроса
UPDATE employees
SET salary = (SELECT AVG(salary) FROM employees WHERE department = 'Разработка')
WHERE name = 'Елена Смирнова';

-- =============================================================================
-- 5. DELETE — удаление строк
-- =============================================================================

-- Удаление с условием
DELETE FROM employees
WHERE name LIKE '%(резерв)%';

-- Удаление всех строк (осторожно! Лучше использовать TRUNCATE для полной очистки)
-- DELETE FROM employees;

-- =============================================================================
-- 6. MERGE — объединение вставки, обновления и удаления (PostgreSQL 15+)
-- =============================================================================

-- Создадим временную таблицу для источника данных
CREATE TEMP TABLE new_salaries (
    name TEXT,
    salary NUMERIC(10, 2)
);

INSERT INTO new_salaries (name, salary)
VALUES
    ('Иван Сидоров', 135000.00),       -- обновить
    ('Ольга Новикова', 90000.00);      -- вставить (новый сотрудник)

-- MERGE: сопоставление по имени
MERGE INTO employees e
USING new_salaries n
ON e.name = n.name
WHEN MATCHED THEN
    UPDATE SET salary = n.salary
WHEN NOT MATCHED THEN
    INSERT (name, department, salary)
    VALUES (n.name, 'Прочее', n.salary);

-- =============================================================================
-- 7. Дополнительно: RETURNING — получение данных изменённых строк
-- (Работает с INSERT, UPDATE, DELETE)
-- =============================================================================

-- INSERT с RETURNING
INSERT INTO employees (name, department, salary)
VALUES ('Дмитрий Кузнецов', 'Поддержка', 70000.00)
RETURNING id, name, salary;

-- UPDATE с RETURNING
UPDATE employees
SET salary = salary + 5000
WHERE department = 'Финансы'
RETURNING *;

-- DELETE с RETURNING
DELETE FROM employees
WHERE salary < 75000
RETURNING name, salary;

-- =============================================================================
-- Завершение: очистка (необязательно)
-- =============================================================================
-- DROP TABLE employees;