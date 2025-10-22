-- Создание таблицы авторов
CREATE TABLE author (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL
);

-- Создание таблицы жанров
CREATE TABLE genre (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- Создание таблицы книг
-- Связь: book.author → author.id, book.genre → genre.id
CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author_id INT NOT NULL REFERENCES author(id) ON DELETE CASCADE,
    genre_id INT NOT NULL REFERENCES genre(id) ON DELETE RESTRICT,
    year INT CHECK (year > 0 AND year <= EXTRACT(YEAR FROM CURRENT_DATE) + 1),
    rating NUMERIC(3, 2) CHECK (rating >= 0.0 AND rating <= 10.0)
);

USERS


USERS_INFO
user_id REFERENCES user.id