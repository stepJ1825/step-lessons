-- Создание таблицы language
CREATE TABLE language (
    id integer PRIMARY KEY,
    language_name VARCHAR(3) NOT NULL UNIQUE,
);
-- Создание таблицы погодных условий
CREATE TABLE precipitation (
    id integer PRIMARY KEY,
    precipitation_name VARCHAR(32) NOT NULL UNIQUE
);

CREATE TABLE citizen_type (
    name VARCHAR(64) NOT NULL UNIQUE PRIMARY KEY,
    language_id REFERENCES language(id)
);

CREATE TABLE my_region (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    square NUMERIC(10, 2),
    citizen_type VARCHAR(64) REFERENCES citizen_type(name)
);

-- Создание таблицы погоды
CREATE TABLE weather (
    id SERIAL PRIMARY KEY,
    my_region INT NOT NULL REFERENCES my_region(id),
    date DATE,
    temperature NUMERIC(3, 2),
    precipitation integer REFERENCES precipitation(id)
);