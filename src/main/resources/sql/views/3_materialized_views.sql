-- 1.Создание материализованного представления (Materialized View)
CREATE MATERIALIZED VIEW IF NOT EXISTS mv_department_summary AS
SELECT
    d.name AS department_name,
    COUNT(e.id) AS total_employees,
    COUNT(CASE WHEN e.is_active THEN 1 END) AS active_employees,
    AVG(e.salary) AS avg_salary,
    SUM(e.salary) AS total_salary
FROM departments d
LEFT JOIN employees e ON d.id = e.department_id
GROUP BY d.name
WITH DATA;

COMMENT ON MATERIALIZED VIEW mv_department_summary IS 'Материализованное представление: сводка по отделам';

-- 2.Обновление материализованного представления
REFRESH MATERIALIZED VIEW mv_department_summary;

-- 3.Просмотр материализованного представления: сводка по отделам
SELECT * FROM mv_department_summary;