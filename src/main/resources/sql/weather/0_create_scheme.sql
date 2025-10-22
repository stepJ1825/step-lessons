-- создаём отдельную схему
-- DROP SCHEMA "weather";
CREATE SCHEMA "weather" AUTHORIZATION postgres;

-- выбираем созданную схему по умолчанию (необязательно)
SET search_path TO weather;