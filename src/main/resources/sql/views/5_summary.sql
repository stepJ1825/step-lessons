-- DROP VIEW IF EXISTS active_employees;
-- DROP VIEW IF EXISTS employees_with_departments;
-- DROP VIEW IF EXISTS department_statistics;
-- DROP VIEW IF EXISTS high_salary_employees;
-- DROP VIEW IF EXISTS project_details;
-- DROP VIEW IF EXISTS salary_fund_report;
-- DROP MATERIALIZED VIEW IF EXISTS mv_department_summary;

------------------------------------------------------------------------------------------------
--Информационные запросы о представлениях

-- Список всех представлений в текущей схеме
SELECT
    table_name AS view_name,
    table_type,
    table_schema
FROM information_schema.tables
WHERE table_type = 'VIEW'
  AND table_schema = 'public'
ORDER BY table_name;

-- Подробная информация о представлении department_statistics
SELECT pg_get_viewdef('department_statistics'::regclass, true) AS view_definition;

-- Права доступа к представлению active_employees
SELECT grantee, privilege_type
FROM information_schema.role_table_grants
WHERE table_name = 'active_employees';