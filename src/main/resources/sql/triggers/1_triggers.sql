-- =============================================================
-- Демонстрация триггеров в PostgreSQL
-- (документация https://postgrespro.ru/docs/postgresql/17/triggers)
-- =============================================================
-- Удаление объектов, если они существуют (для повторного запуска)
DROP TRIGGER IF EXISTS audit_employee_insert ON employees;
DROP TRIGGER IF EXISTS audit_employee_update ON employees;
DROP TRIGGER IF EXISTS audit_employee_delete ON employees;
DROP FUNCTION IF EXISTS log_employee_change();
DROP TABLE IF EXISTS employee_audit;
DROP TABLE IF EXISTS employees;

-- Создание таблицы сотрудников
CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    salary DECIMAL(10, 2),
    department VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Создание таблицы аудита
CREATE TABLE employee_audit (
    audit_id SERIAL PRIMARY KEY,
    employee_id INT,
    action VARCHAR(10),           -- 'INSERT', 'UPDATE', 'DELETE'
    old_data JSONB,
    new_data JSONB,
    changed_by VARCHAR(50) DEFAULT CURRENT_USER,
    changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Создание функции-триггера для логирования изменений
CREATE OR REPLACE FUNCTION log_employee_change()
RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'INSERT' THEN
        INSERT INTO employee_audit (employee_id, action, new_data)
        VALUES (NEW.id, 'INSERT', row_to_json(NEW)::JSONB);
        RETURN NEW;

    ELSIF TG_OP = 'UPDATE' THEN
        INSERT INTO employee_audit (employee_id, action, old_data, new_data)
        VALUES (NEW.id, 'UPDATE', row_to_json(OLD)::JSONB, row_to_json(NEW)::JSONB);
        RETURN NEW;

    ELSIF TG_OP = 'DELETE' THEN
        INSERT INTO employee_audit (employee_id, action, old_data)
        VALUES (OLD.id, 'DELETE', row_to_json(OLD)::JSONB);
        RETURN OLD;
    END IF;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

-- Создание триггеров на таблицу employees
CREATE TRIGGER audit_employee_insert
    AFTER INSERT ON employees
    FOR EACH ROW EXECUTE FUNCTION log_employee_change();

CREATE TRIGGER audit_employee_update
    AFTER UPDATE ON employees
    FOR EACH ROW EXECUTE FUNCTION log_employee_change();

CREATE TRIGGER audit_employee_delete
    AFTER DELETE ON employees
    FOR EACH ROW EXECUTE FUNCTION log_employee_change();

-- =============================================================
-- Демонстрация работы триггеров
-- =============================================================

-- Вставка данных
INSERT INTO employees (name, email, salary, department) VALUES
('Иван Иванов', 'ivan.ivanov@example.com', 50000.00, 'IT'),
('Мария Петрова', 'maria.petrova@example.com', 60000.00, 'HR'),
('Алексей Сидоров', 'alexey.sidorov@example.com', 55000.00, 'IT');

-- Обновление данных
UPDATE employees SET salary = 58000.00 WHERE name = 'Иван Иванов';
UPDATE employees SET department = 'Finance' WHERE email = 'maria.petrova@example.com';

-- Удаление данных
DELETE FROM employees WHERE name = 'Алексей Сидоров';

-- Проверка результатов в таблице сотрудников
SELECT 'Таблица employees' AS source, * FROM employees;

-- Проверка результатов в таблице аудита
SELECT 'Таблица employee_audit' AS source, audit_id, action, employee_id, old_data, new_data, changed_by, changed_at
FROM employee_audit
ORDER BY audit_id;