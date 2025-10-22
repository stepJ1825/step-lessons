-- создаём отдельную схему
-- DROP SCHEMA "library";
CREATE SCHEMA "library" AUTHORIZATION postgres;

-- выбираем созданную схему по умолчанию (необязательно)
SET search_path TO library;