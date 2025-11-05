-- =============================================================================
-- Файл: text_and_cast_examples.sql
-- Описание: Примеры работы с текстовыми данными и приведения типов в PostgreSQL
-- =============================================================================

-- 1. Подготовка: создание тестовой таблицы
CREATE TEMP TABLE demo_data (
    id SERIAL,
    raw_value TEXT,
    created_at TIMESTAMP DEFAULT NOW()
);

INSERT INTO demo_data (raw_value) VALUES
    ('123'),
    ('45.67'),
    ('2023-10-05'),
    ('  Hello, World!  '),
    ('АНАЛИТИКА'),
    ('42abc'),
    (NULL),
    ('true'),
    ('[1,2,3]'),
    ('{"name": "Ivan", "age": 30}');

-- =============================================================================
-- 2. Работа с текстом (строковыми функциями)
-- =============================================================================

-- 2.1. Регистр
SELECT
    raw_value,
    UPPER(raw_value) AS upper_val,
    LOWER(raw_value) AS lower_val,
    INITCAP(raw_value) AS initcap_val  -- первая буква каждого слова — заглавная
FROM demo_data
WHERE raw_value IS NOT NULL;

-- 2.2. Обрезка пробелов
SELECT
    raw_value,
    TRIM(raw_value) AS trimmed,
    LTRIM(raw_value) AS ltrimmed,
    RTRIM(raw_value) AS rtrimmed
FROM demo_data
WHERE raw_value LIKE ' %';

-- 2.3. Длина и подстроки
SELECT
    raw_value,
    LENGTH(raw_value) AS char_length,
    CHAR_LENGTH(raw_value) AS char_length2,
    OCTET_LENGTH(raw_value) AS byte_length,  -- длина в байтах (важно для UTF-8)
    SUBSTRING(raw_value FROM 1 FOR 5) AS first_5_chars,
    RIGHT(raw_value, 3) AS last_3_chars,
    LEFT(raw_value, 2) AS first_2_chars
FROM demo_data
WHERE raw_value IS NOT NULL;

-- 2.4. Поиск и замена
SELECT
    raw_value,
    POSITION('World' IN raw_value) AS pos_world,  -- 0 если не найдено
    STRPOS(raw_value, 'World') AS strpos_world,  -- то же, но возвращает 0
    REPLACE(raw_value, 'World', 'PostgreSQL') AS replaced,
    TRANSLATE(raw_value, 'aeiou', 'AEIOU') AS vowels_to_upper  -- посимвольная замена
FROM demo_data
WHERE raw_value LIKE '%World%';

-- 2.5. Регулярные выражения
SELECT
    raw_value,
    -- Совпадает ли строка с шаблоном (число)
    raw_value ~ '^\d+$' AS is_integer,
    -- Замена по регулярке: удалить всё, кроме цифр
    REGEXP_REPLACE(raw_value, '\D', '', 'g') AS digits_only, --TODO: пустые аргументы
    -- Извлечь email (пример)
    NULLIF(REGEXP_SUBSTR('ivan@example.com', '\w+@\w+\.\w+'), '') AS email, --TODO: пустые аргументы
    NULLIF(REGEXP_SUBSTR('ivan@exampl', '\w+@\w+\.\w+'), '') AS email2 --TODO: пустые аргументы
FROM demo_data;

-- =============================================================================
-- 3. Приведение типов данных (Type Casting)
-- =============================================================================

-- 3.1. Явное приведение с помощью :: (PostgreSQL-синтаксис)
SELECT
    raw_value,
--    raw_value::INTEGER AS to_int,              -- ошибка, если нельзя
--    raw_value::NUMERIC AS to_num              -- работает с дробями
--    raw_value::DATE AS to_date                -- только для '2023-10-05'
--    '1'::BOOLEAN AS to_bool             -- 'true' → true, '1' → true и т.д.
    raw_value::JSON AS to_json                 -- только для валидного JSON
FROM demo_data
WHERE raw_value IN (
--'123',
--'45.67'
--'2023-10-05'
--'true'
'{"name": "Ivan", "age": 30}'
);

-- 3.2. Безопасное приведение с помощью CAST
-- Аналогично, но стандартный SQL-синтаксис
SELECT
    raw_value,
    CAST(raw_value AS INTEGER) AS cast_int
FROM demo_data
WHERE raw_value = '123';

-- 3.3. Безопасное преобразование с обработкой ошибок
-- PostgreSQL не имеет TRY_CAST, но можно использовать функции:
CREATE OR REPLACE FUNCTION try_cast_int(text)
RETURNS INTEGER AS $$
BEGIN
    RETURN $1::INTEGER;
EXCEPTION WHEN invalid_text_representation THEN
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

SELECT
    raw_value,
    try_cast_int(raw_value) AS safe_int
FROM demo_data;

-- 3.4. Приведение к TEXT (часто нужно при конкатенации)
SELECT
    id::TEXT || ' - ' || COALESCE(raw_value, 'some null text') AS id_label
FROM demo_data;

-- 3.5. Работа с JSON через приведение
SELECT
    raw_value::JSON->>'name' AS json_name,
    (raw_value::JSON->'age')::TEXT::INTEGER AS json_age
FROM demo_data
WHERE raw_value LIKE '{"name":%';

-- =============================================================================
-- 4. Полезные комбинации: текст + приведение
-- =============================================================================

-- 4.1. Извлечь число из строки и привести
SELECT
    raw_value,
    NULLIF(REGEXP_REPLACE(raw_value, '\D', '', 'g'), '')::INTEGER AS extracted_number
FROM demo_data
WHERE raw_value = '42abc';

-- 4.2. Нормализация: привести к верхнему регистру и обрезать
SELECT
    INITCAP(SUBSTRING(TRIM(UPPER(raw_value)) from 1 for 5)) AS normalized
FROM demo_data
WHERE raw_value IS NOT NULL;

-- 4.3. Форматирование даты из строки
SELECT TO_CHAR(raw_value::DATE, 'DD Month YY') AS formatted_date
FROM demo_data
WHERE raw_value = '2023-10-05';

-- =============================================================================
-- 5. Дополнительно: кодировки и локали
-- =============================================================================

-- Проверить текущую локаль
SHOW lc_collate;   -- влияет на сортировку
SHOW client_encoding;  -- обычно UTF8

-- Сравнение с учётом локали
SELECT 'Ёж' > 'Енот' COLLATE "ru_RU";  -- true в русской локали

-- =============================================================================
-- 6. Округление
-- =============================================================================
SELECT ROUND(12.3456, 2);  -- Результат: 12.35
SELECT ROUND(3.14159, 3);  -- Результат: 3.142
SELECT ROUND(5.55, 1);    -- Результат: 5.6

SELECT 12.3456::NUMERIC(10,2);  -- Результат: 12.35
SELECT 9.999::NUMERIC(4,2);     -- Результат: 10.00 (округлилось и поместилось)
-- Но:
-- SELECT 99.999::NUMERIC(3,1); -- Ошибка! Превышает общее число цифр

SELECT TRUNC(12.987, 2);  -- Результат: 12.98 -- TRUNC(value, ndigits) — отсечение, а не округление

--округление «всегда вверх» - CEIL,  «всегда вниз» - FLOOR.
select CEIL(12.4) as ceil, floor(12.6) as floor

-- =============================================================================
-- 7. REGEXP_REPLACE flags
-- =============================================================================
-- DOCS: https://postgrespro.ru/docs/postgresql/17/functions-matching
--g   global — заменить все совпадения
--i   case-insensitive — игнорировать регистр
--c   case-sensitive — учитывать регистр (по умолчанию)
--m   multiline   — ^ и $ привязываются к началу/концу строк (а не всего текста)
--n   newline-sensitive — точка (.) не совпадает с \n
--p   partial newline-sensitive — как n, но ^ и $ также работают с \n