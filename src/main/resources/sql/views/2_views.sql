-- 1.Простое представление - список активных сотрудников
CREATE OR REPLACE VIEW active_employees AS
SELECT
    id,
    first_name,
    last_name,
    email,
    hire_date,
    salary,
    position
FROM employees
WHERE is_active = true;

COMMENT ON VIEW active_employees IS 'Представление: список всех активных сотрудников';

-- 2.Представление с JOIN - сотрудники с названиями отделов
CREATE OR REPLACE VIEW employees_with_departments AS
SELECT
    e.id AS employee_id,
    e.first_name,
    e.last_name,
    e.email,
    e.salary,
    e.position,
    d.name AS department_name,
    d.location AS department_location
FROM employees e
JOIN departments d ON e.department_id = d.id;

COMMENT ON VIEW employees_with_departments IS 'Представление: сотрудники с информацией об их отделах';

-- 3.Представление с агрегацией - статистика по отделам
CREATE OR REPLACE VIEW department_statistics AS
SELECT
    d.id AS department_id,
    d.name AS department_name,
    d.location,
    d.budget AS department_budget,
    COUNT(e.id) AS employee_count,
    COALESCE(AVG(e.salary), 0) AS average_salary,
    COALESCE(SUM(e.salary), 0) AS total_salary,
    MAX(e.salary) AS max_salary,
    MIN(e.salary) AS min_salary
FROM departments d
LEFT JOIN employees e ON d.id = e.department_id AND e.is_active = true
GROUP BY d.id, d.name, d.location, d.budget
ORDER BY employee_count DESC;

COMMENT ON VIEW department_statistics IS 'Представление: статистика по отделам (количество сотрудников, средняя зарплата и т.д.)';

-- 4.Представление с фильтрацией и сортировкой - высокооплачиваемые сотрудники
CREATE OR REPLACE VIEW high_salary_employees AS
SELECT
    e.id,
    e.first_name,
    e.last_name,
    e.salary,
    d.name AS department_name,
    e.position
FROM employees e
JOIN departments d ON e.department_id = d.id
WHERE e.salary > 80000
  AND e.is_active = true
ORDER BY e.salary DESC;

COMMENT ON VIEW high_salary_employees IS 'Представление: сотрудники с зарплатой выше $80,000';

-- 5.Представление с несколькими JOIN и CASE - детальная информация о проектах
CREATE OR REPLACE VIEW project_details AS
SELECT
    p.id AS project_id,
    p.name AS project_name,
    p.description,
    p.start_date,
    p.end_date,
    p.budget AS project_budget,
    d.name AS department_name,
    d.location AS department_location,
    COUNT(e.id) AS team_size,
    CASE
        WHEN p.end_date < CURRENT_DATE THEN 'Completed'
        WHEN p.start_date > CURRENT_DATE THEN 'Not Started'
        ELSE 'In Progress'
    END AS project_status,
    ROUND((EXTRACT(EPOCH FROM (CURRENT_DATE - p.start_date)) /
           EXTRACT(EPOCH FROM (p.end_date - p.start_date))) * 100, 2) AS completion_percentage
FROM projects p
JOIN departments d ON p.department_id = d.id
LEFT JOIN employees e ON e.department_id = d.id AND e.is_active = true
GROUP BY p.id, p.name, p.description, p.start_date, p.end_date, p.budget, d.name, d.location;

COMMENT ON VIEW project_details IS 'Представление: детальная информация о проектах с их статусом';

-- 6.Представление для отчетности - зарплатный фонд по отделам
CREATE OR REPLACE VIEW salary_fund_report AS
SELECT
    d.name AS department_name,
    COUNT(e.id) AS active_employees,
    SUM(e.salary) AS total_salary_fund,
    ROUND(SUM(e.salary) / d.budget * 100, 2) AS budget_percentage,
    CASE
        WHEN SUM(e.salary) / d.budget > 0.7 THEN 'High'
        WHEN SUM(e.salary) / d.budget > 0.5 THEN 'Medium'
        ELSE 'Low'
    END AS budget_utilization
FROM departments d
LEFT JOIN employees e ON d.id = e.department_id AND e.is_active = true
GROUP BY d.id, d.name, d.budget
HAVING SUM(e.salary) > 0
ORDER BY total_salary_fund DESC;

COMMENT ON VIEW salary_fund_report IS 'Представление: отчет по зарплатному фонду отделов';
--------------------------------------------------------------------------------------------------------
-- 7.Демонстрация использования представлений

-- Просмотр активных сотрудников
SELECT * FROM active_employees ORDER BY last_name, first_name;

-- Просмотр сотрудников с отделами
SELECT * FROM employees_with_departments
WHERE department_name = 'IT'
ORDER BY salary DESC;

-- Просмотр статистики по отделам
SELECT * FROM department_statistics;

-- Просмотр высокооплачиваемых сотрудников
SELECT * FROM high_salary_employees;

-- Просмотр деталей проектов
SELECT * FROM project_details;

-- Просмотр отчета по зарплатному фонду
SELECT * FROM salary_fund_report;





