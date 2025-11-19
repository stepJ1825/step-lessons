-- pg_stat_statements_demo.sql
-- Демонстрация работы с расширением pg_stat_statements
-- Важно: для работы этого расширения нужны права суперпользователя или специальные права

-- =============================================================
-- 1. УСТАНОВКА И НАСТРОЙКА РАСШИРЕНИЯ
-- =============================================================

-- Установка расширения (выполняется один раз)
CREATE EXTENSION IF NOT EXISTS pg_stat_statements;

-- Примечание: также необходимо добавить в postgresql.conf:
-- shared_preload_libraries = 'pg_stat_statements'
-- pg_stat_statements.track = all
-- pg_stat_statements.max = 10000
-- pg_stat_statements.save = on
-- После изменения конфигурации требуется перезагрузка сервера:
-- SELECT pg_reload_conf();

-- Проверка установки расширения
SELECT
    extname AS "Имя расширения",
    extversion AS "Версия",
    extconfig AS "Конфигурация"
FROM pg_extension
WHERE extname = 'pg_stat_statements';

-- =============================================================
-- 2. ОСНОВНЫЕ ПОЛЯ pg_stat_statements
-- =============================================================
-- Представление pg_stat_statements содержит следующие основные поля:
-- userid       - OID пользователя
-- dbid         - OID базы данных
-- queryid      - Хэш запроса
-- query        - Сам текст запроса (с параметрами)
-- calls        - Количество вызовов
-- total_time   - Общее время выполнения в миллисекундах
-- min_time     - Минимальное время выполнения
-- max_time     - Максимальное время выполнения
-- mean_time    - Среднее время выполнения
-- stddev_time  - Стандартное отклонение времени
-- rows         - Общее количество обработанных строк
-- shared_blks_hit    - Количество попаданий в кэш буферов
-- shared_blks_read   - Количество прочитанных блоков из диска
-- shared_blks_dirtied- Количество "загрязненных" блоков
-- shared_blks_written- Количество записанных блоков
-- local_blks_hit     - Локальные блоки (для временных таблиц)
-- local_blks_read    - Локальные блоки (чтение)
-- local_blks_dirtied - Локальные блоки (изменение)
-- local_blks_written - Локальные блоки (запись)
-- temp_blks_read     - Временные блоки (чтение)
-- temp_blks_written  - Временные блоки (запись)
-- blk_read_time      - Время чтения блоков в миллисекундах
-- blk_write_time     - Время записи блоков в миллисекундах

-- =============================================================
-- 3. ПРОСТЫЕ ЗАПРОСЫ ДЛЯ АНАЛИЗА ПРОИЗВОДИТЕЛЬНОСТИ
-- =============================================================

-- Топ 10 самых медленных запросов по общему времени выполнения
SELECT
    queryid::text AS "ID запроса",
    LEFT(query, 80) || CASE WHEN LENGTH(query) > 80 THEN '...' ELSE '' END AS "Запрос (обрезанный)",
    calls AS "Вызовов",
    ROUND(total_time::numeric, 2) AS "Общее время (мс)",
    ROUND(mean_time::numeric, 2) AS "Среднее время (мс)",
    rows AS "Обработано строк",
    ROUND((total_time / SUM(total_time) OVER ()) * 100, 2) AS "% от общего времени"
FROM pg_stat_statements
ORDER BY total_time DESC
LIMIT 10;

-- Топ 10 запросов с самым большим количеством вызовов
SELECT
    queryid::text AS "ID запроса",
    LEFT(query, 80) || CASE WHEN LENGTH(query) > 80 THEN '...' ELSE '' END AS "Запрос (обрезанный)",
    calls AS "Вызовов",
    ROUND(total_time::numeric, 2) AS "Общее время (мс)",
    ROUND(mean_time::numeric, 2) AS "Среднее время (мс)",
    ROUND((calls::numeric / SUM(calls) OVER ()) * 100, 2) AS "% от общего количества"
FROM pg_stat_statements
ORDER BY calls DESC
LIMIT 10;

-- Топ 10 запросов с самым большим количеством обработанных строк
SELECT
    queryid::text AS "ID запроса",
    LEFT(query, 80) || CASE WHEN LENGTH(query) > 80 THEN '...' ELSE '' END AS "Запрос (обрезанный)",
    calls AS "Вызовов",
    rows AS "Обработано строк",
    ROUND(rows::numeric / calls, 2) AS "Строк на вызов",
    ROUND(mean_time::numeric, 2) AS "Среднее время (мс)"
FROM pg_stat_statements
WHERE calls > 0
ORDER BY rows DESC
LIMIT 10;

-- =============================================================
-- 4. АНАЛИЗ ИСПОЛЬЗОВАНИЯ ПАМЯТИ И ДИСКА
-- =============================================================

-- Топ 10 запросов с самым большим количеством чтений с диска
SELECT
    queryid::text AS "ID запроса",
    LEFT(query, 80) || CASE WHEN LENGTH(query) > 80 THEN '...' ELSE '' END AS "Запрос (обрезанный)",
    shared_blks_read AS "Прочитано блоков",
    ROUND(blk_read_time::numeric, 2) AS "Время чтения (мс)",
    ROUND(shared_blks_read::numeric / calls, 2) AS "Блоков на вызов",
    ROUND(mean_time::numeric, 2) AS "Среднее время (мс)"
FROM pg_stat_statements
WHERE calls > 0
ORDER BY shared_blks_read DESC
LIMIT 10;

-- Топ 10 запросов с самым большим количеством записей на диск
SELECT
    queryid::text AS "ID запроса",
    LEFT(query, 80) || CASE WHEN LENGTH(query) > 80 THEN '...' ELSE '' END AS "Запрос (обрезанный)",
    shared_blks_written AS "Записано блоков",
    ROUND(blk_write_time::numeric, 2) AS "Время записи (мс)",
    ROUND(shared_blks_written::numeric / calls, 2) AS "Блоков на вызов",
    ROUND(mean_time::numeric, 2) AS "Среднее время (мс)"
FROM pg_stat_statements
WHERE calls > 0
ORDER BY shared_blks_written DESC
LIMIT 10;

-- Анализ использования кэша (попадания в кэш)
SELECT
    queryid::text AS "ID запроса",
    LEFT(query, 80) || CASE WHEN LENGTH(query) > 80 THEN '...' ELSE '' END AS "Запрос (обрезанный)",
    shared_blks_hit AS "Попаданий в кэш",
    shared_blks_read AS "Чтений с диска",
    ROUND(
        CASE
            WHEN (shared_blks_hit + shared_blks_read) > 0
            THEN (shared_blks_hit::numeric / (shared_blks_hit + shared_blks_read)) * 100
            ELSE 0
        END, 2) AS "Hit ratio (%)",
    calls AS "Вызовов",
    ROUND(mean_time::numeric, 2) AS "Среднее время (мс)"
FROM pg_stat_statements
WHERE calls > 0
  AND (shared_blks_hit + shared_blks_read) > 0
ORDER BY shared_blks_hit + shared_blks_read DESC
LIMIT 10;

-- =============================================================
-- 5. ПРОДВИНУТЫЕ ЗАПРОСЫ ДЛЯ ОПТИМИЗАЦИИ
-- =============================================================

-- Запросы с высоким временем выполнения на вызов
SELECT
    queryid::text AS "ID запроса",
    LEFT(query, 80) || CASE WHEN LENGTH(query) > 80 THEN '...' ELSE '' END AS "Запрос (обрезанный)",
    calls AS "Вызовов",
    ROUND(mean_time::numeric, 2) AS "Среднее время (мс)",
    ROUND(stddev_time::numeric, 2) AS "Стандартное отклонение (мс)",
    rows AS "Обработано строк",
    shared_blks_read AS "Чтений с диска"
FROM pg_stat_statements
WHERE calls > 10  -- Игнорируем редкие запросы
  AND mean_time > 100  -- Более 100 мс в среднем
ORDER BY mean_time DESC
LIMIT 10;

-- Запросы, генерирующие много временных файлов
SELECT
    queryid::text AS "ID запроса",
    LEFT(query, 80) || CASE WHEN LENGTH(query) > 80 THEN '...' ELSE '' END AS "Запрос (обрезанный)",
    temp_blks_read AS "Чтений временных блоков",
    temp_blks_written AS "Записей временных блоков",
    ROUND(mean_time::numeric, 2) AS "Среднее время (мс)",
    calls AS "Вызовов"
FROM pg_stat_statements
WHERE temp_blks_read > 0 OR temp_blks_written > 0
ORDER BY temp_blks_written DESC
LIMIT 10;

-- Анализ по пользователям и базам данных
SELECT
    COALESCE(rolname, 'unknown') AS "Пользователь",
    COALESCE(datname, 'unknown') AS "База данных",
    COUNT(*) AS "Количество запросов",
    SUM(calls) AS "Общее количество вызовов",
    ROUND(SUM(total_time)::numeric, 2) AS "Общее время (мс)",
    ROUND(AVG(mean_time)::numeric, 2) AS "Среднее время (мс)"
FROM pg_stat_statements s
LEFT JOIN pg_roles r ON s.userid = r.oid
LEFT JOIN pg_database d ON s.dbid = d.oid
GROUP BY rolname, datname
ORDER BY SUM(total_time) DESC;

-- =============================================================
-- 6. ПРАКТИЧЕСКИЕ СОВЕТЫ И ОЧИСТКА СТАТИСТИКИ
-- =============================================================

-- Сброс статистики (требует прав суперпользователя)
-- SELECT pg_stat_statements_reset();

-- Просмотр полного текста конкретного запроса по queryid
-- SELECT query FROM pg_stat_statements WHERE queryid = <ваш_queryid>;

-- =============================================================
-- 7. ПОЛЕЗНЫЕ ФУНКЦИИ ДЛЯ АНАЛИЗА
-- =============================================================

-- Функция для получения детальной информации о запросе
SELECT
    'pg_stat_statements_info()' AS "Функция",
    'Возвращает общую информацию о состоянии расширения' AS "Описание"
UNION ALL
SELECT
    'pg_stat_statements_reset()',
    'Сбрасывает всю собранную статистику'
UNION ALL
SELECT
    'pg_stat_statements(false)',
    'Возвращает статистику без сброса счетчиков'
UNION ALL
SELECT
    'pg_stat_statements(true)',
    'Возвращает статистику и сбрасывает счетчики';

-- =============================================================
-- 8. ЗАКЛЮЧЕНИЕ И РЕКОМЕНДАЦИИ
-- =============================================================
--Ключевые показатели для оптимизации:
--- Среднее время выполнения (mean_time) > 100 мс требует внимания
--- Низкий hit ratio (< 90%) указывает на нехватку shared_buffers
--- Большое количество temp_blks_written говорит о нехватке work_mem
--- Высокое stddev_time указывает на непредсказуемую производительность
--Рекомендации:
--- Оптимизируйте запросы с высоким временем выполнения
--- Добавьте индексы для запросов с большим количеством чтений
--- Увеличьте work_mem для запросов с большими временными файлами
--- Увеличьте shared_buffers для улучшения hit ratio
--- Используйте prepared statements для часто вызываемых запросов
--Важные замечания:
--- Статистика сбрасывается при перезагрузке сервера
--- Для production систем мониторьте регулярно
--- pg_stat_statements потребляет дополнительную память
--- Настройте pg_stat_statements.max в зависимости от нагрузки