-- =============================================================
-- 1. ИНФОРМАЦИЯ О ТАБЛИЦАХ И ПРЕДСТАВЛЕНИЯХ
-- =============================================================

-- Все таблицы и представления в схеме public
SELECT
    table_name,
    table_type,
    table_schema
FROM information_schema.tables
WHERE table_schema = 'bookings'
ORDER BY table_type, table_name;

-- Только базовые таблицы
SELECT table_name
FROM information_schema.tables
WHERE table_type = 'BASE TABLE'
  AND table_schema = 'public'
ORDER BY table_name;

-- Только представления (views)
SELECT table_name
FROM information_schema.views
WHERE table_schema = 'bookings'
ORDER BY table_name;

-- =============================================================
-- 2. ИНФОРМАЦИЯ О СТОЛБЦАХ
-- =============================================================

-- Структура столбцов для всех таблиц
SELECT
    table_name,
    column_name,
    data_type,
    character_maximum_length AS max_length,
    numeric_precision,
    numeric_scale,
    is_nullable,
    column_default
FROM information_schema.columns
WHERE table_schema = 'bookings'
ORDER BY table_name, ordinal_position;

-- Детальная информация о столбцах конкретной таблицы (пример для таблицы airplanes_data)
SELECT
    column_name AS "Имя столбца",
    data_type AS "Тип данных",
    COALESCE(character_maximum_length::text, '') AS "Макс. длина",
    COALESCE(numeric_precision::text, '') AS "Точность",
    COALESCE(numeric_scale::text, '') AS "Масштаб",
    is_nullable AS "NULL?",
    COALESCE(column_default, '') AS "Значение по умолчанию"
FROM information_schema.columns
WHERE table_name = 'airplanes_data'
  AND table_schema = 'bookings'
ORDER BY ordinal_position;

-- =============================================================
-- 3. ИНФОРМАЦИЯ О КЛЮЧАХ И ОГРАНИЧЕНИЯХ
-- =============================================================

-- Первичные ключи
SELECT
    tc.table_name AS "Таблица",
    kcu.column_name AS "Столбец",
    tc.constraint_name AS "Имя ограничения"
FROM information_schema.table_constraints tc
JOIN information_schema.key_column_usage kcu
  ON tc.constraint_name = kcu.constraint_name
WHERE tc.constraint_type = 'PRIMARY KEY'
  AND tc.table_schema = 'bookings'
ORDER BY tc.table_name, kcu.ordinal_position;

-- Внешние ключи
SELECT
    tc.table_name AS "Таблица",
    kcu.column_name AS "Столбец",
    ccu.table_name AS "Внешняя таблица",
    ccu.column_name AS "Внешний столбец",
    tc.constraint_name AS "Имя ограничения"
FROM information_schema.table_constraints tc
JOIN information_schema.key_column_usage kcu
  ON tc.constraint_name = kcu.constraint_name
JOIN information_schema.constraint_column_usage ccu
  ON tc.constraint_name = ccu.constraint_name
WHERE tc.constraint_type = 'FOREIGN KEY'
  AND tc.table_schema = 'bookings'
ORDER BY tc.table_name, kcu.ordinal_position;

-- CHECK ограничения
SELECT
    tc.table_name AS "Таблица",
    tc.constraint_name AS "Имя ограничения",
    cc.check_clause AS "Условие"
FROM information_schema.table_constraints tc
JOIN information_schema.check_constraints cc
  ON tc.constraint_name = cc.constraint_name
WHERE tc.table_schema = 'bookings'
ORDER BY tc.table_name, tc.constraint_name;

-- =============================================================
-- 4. ИНФОРМАЦИЯ О ФУНКЦИЯХ И ПРОЦЕДУРАХ
-- =============================================================

-- Все функции и процедуры
SELECT
    routine_name AS "Имя",
    routine_type AS "Тип",
    data_type AS "Тип возврата",
    character_maximum_length AS "Макс. длина",
    numeric_precision AS "Точность"
FROM information_schema.routines
WHERE routine_schema = 'bookings'
ORDER BY routine_type, routine_name;

-- Параметры функций
SELECT
    specific_name AS "Имя функции",
    parameter_name AS "Имя параметра",
    ordinal_position AS "Позиция",
    data_type AS "Тип данных",
    character_maximum_length AS "Макс. длина"
FROM information_schema.parameters
WHERE specific_schema = 'bookings'
ORDER BY specific_name, ordinal_position;

-- =============================================================
-- 5. ИНФОРМАЦИЯ О ТРИГГЕРАХ
-- =============================================================

-- Все триггеры
SELECT
    trigger_name AS "Имя триггера",
    event_manipulation AS "Событие",
    event_object_table AS "Таблица",
    action_statement AS "Действие",
    action_timing AS "Время выполнения"
FROM postgres.information_schema.triggers
WHERE trigger_schema = 'public'
ORDER BY event_object_table, trigger_name;

-- =============================================================
-- 6. ИНФОРМАЦИЯ О ПРАВАХ ДОСТУПА
-- =============================================================

-- Права на таблицы
SELECT
    table_name AS "Таблица",
    grantee AS "Пользователь/Роль",
    privilege_type AS "Привилегия",
    is_grantable AS "С возможностью передачи"
FROM information_schema.role_table_grants
WHERE table_schema = 'bookings'
ORDER BY table_name, grantee, privilege_type;

-- Права на столбцы
SELECT
    table_name AS "Таблица",
    column_name AS "Столбец",
    grantee AS "Пользователь/Роль",
    privilege_type AS "Привилегия"
FROM information_schema.role_column_grants
WHERE table_schema = 'bookings'
ORDER BY table_name, column_name, grantee;

-- =============================================================
-- 7. ИНФОРМАЦИЯ О ДОМЕНАХ И ТИПАХ ДАННЫХ
-- =============================================================

-- Пользовательские домены
SELECT
    domain_name AS "Имя домена",
    data_type AS "Тип данных",
    character_maximum_length AS "Макс. длина",
    numeric_precision AS "Точность",
    numeric_scale AS "Масштаб",
    domain_default AS "Значение по умолчанию"
FROM information_schema.domains
WHERE domain_schema = 'public'
ORDER BY domain_name;

-- =============================================================
-- 8. КОМПЛЕКСНЫЕ ПРИМЕРЫ
-- =============================================================

-- Комплексная информация о конкретной таблице
WITH table_info AS (
    SELECT
        t.table_name,
        t.table_type,
        c.column_name,
        c.data_type,
        c.character_maximum_length,
        c.numeric_precision,
        c.numeric_scale,
        c.is_nullable,
        c.column_default,
        tc.constraint_type,
        tc.constraint_name,
        fk.foreign_table_name,
        fk.foreign_column_name
    FROM information_schema.tables t
    LEFT JOIN information_schema.columns c ON t.table_name = c.table_name
    LEFT JOIN (
        SELECT kcu.table_name, kcu.column_name, tc.constraint_type, tc.constraint_name
        FROM information_schema.table_constraints tc
        JOIN information_schema.key_column_usage kcu ON tc.constraint_name = kcu.constraint_name
        WHERE tc.constraint_schema = 'bookings'
    ) tc ON t.table_name = tc.table_name AND c.column_name = tc.column_name
    LEFT JOIN (
        SELECT
            kcu.table_name,
            kcu.column_name,
            ccu.table_name AS foreign_table_name,
            ccu.column_name AS foreign_column_name
        FROM information_schema.table_constraints tc
        JOIN information_schema.key_column_usage kcu ON tc.constraint_name = kcu.constraint_name
        JOIN information_schema.constraint_column_usage ccu ON tc.constraint_name = ccu.constraint_name
        WHERE tc.constraint_type = 'FOREIGN KEY'
          AND tc.constraint_schema = 'bookings'
    ) fk ON t.table_name = fk.table_name AND c.column_name = fk.column_name
    WHERE t.table_schema = 'bookings'
      AND t.table_name = 'flights'
)
SELECT
    column_name AS "Столбец",
    data_type AS "Тип данных",
    COALESCE(character_maximum_length::text, numeric_precision::text || ',' || numeric_scale::text, '') AS "Параметры",
    is_nullable AS "NULL?",
    COALESCE(column_default, '') AS "По умолчанию",
    COALESCE(constraint_type, '') AS "Ограничение",
    COALESCE(foreign_table_name || '.' || foreign_column_name, '') AS "Внешний ключ"
FROM table_info
ORDER BY column_name;

-- Поиск объектов по ключевому слову
SELECT
    'TABLE' AS object_type,
    table_name AS object_name,
    '' AS additional_info
FROM information_schema.tables
WHERE table_name LIKE '%air%'
  AND table_schema = 'bookings'
UNION ALL
SELECT
    'VIEW',
    table_name,
    ''
FROM information_schema.views
WHERE table_name LIKE '%air%'
  AND table_schema = 'bookings'
UNION ALL
SELECT
    'COLUMN',
    column_name || ' in ' || table_name,
    data_type
FROM information_schema.columns
WHERE column_name LIKE '%air%'
  AND table_schema = 'bookings'
UNION ALL
SELECT
    'ROUTINE',
    routine_name,
    routine_type || ' -> ' || data_type
FROM information_schema.routines
WHERE routine_name LIKE '%air%'
  AND routine_schema = 'bookings'
ORDER BY object_type, object_name;

-- =============================================================
-- 9. СИСТЕМНАЯ ИНФОРМАЦИЯ
-- =============================================================

-- Поддерживаемые типы данных
SELECT
    data_type AS "Тип данных",
    COUNT(*) AS "Количество использований"
FROM information_schema.columns
WHERE table_schema = 'bookings'
GROUP BY data_type
ORDER BY COUNT(*) DESC;

-- Схемы базы данных
SELECT
    schema_name AS "Имя схемы",
    schema_owner AS "Владелец"
FROM information_schema.schemata
ORDER BY schema_name;
