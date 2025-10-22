-- Вставка авторов
INSERT INTO author (first_name, surname) VALUES
    ('Лев', 'Толстой'),
    ('Фёдор', 'Достоевский'),
    ('Антуан', 'де Сент-Экзюпери'),
    ('Джордж', 'Оруэлл'),
    ('Михаил', 'Булгаков');

-- Вставка жанров
INSERT INTO genre (name) VALUES
    ('Роман'),
    ('Повесть'),
    ('Антиутопия'),
    ('Фантастика'),
    ('Сказка');

-- Вставка книг
INSERT INTO book (title, author_id, genre_id, year, rating) VALUES
    ('Война и мир',
        (SELECT id FROM author WHERE first_name = 'Лев' AND surname = 'Толстой'),
        (SELECT id FROM genre WHERE name = 'Роман'),
        1869, 9.30),

    ('Анна Каренина',
        (SELECT id FROM author WHERE first_name = 'Лев' AND surname = 'Толстой'),
        (SELECT id FROM genre WHERE name = 'Роман'),
        1877, 9.10),

    ('Преступление и наказание',
        (SELECT id FROM author WHERE first_name = 'Фёдор' AND surname = 'Достоевский'),
        (SELECT id FROM genre WHERE name = 'Роман'),
        1866, 9.20),

    ('Маленький принц',
        (SELECT id FROM author WHERE first_name = 'Антуан' AND surname = 'де Сент-Экзюпери'),
        (SELECT id FROM genre WHERE name = 'Сказка'),
        1943, 9.40),

    ('1984',
        (SELECT id FROM author WHERE first_name = 'Джордж' AND surname = 'Оруэлл'),
        (SELECT id FROM genre WHERE name = 'Антиутопия'),
        1949, 9.50),

    ('Мастер и Маргарита',
        (SELECT id FROM author WHERE first_name = 'Михаил' AND surname = 'Булгаков'),
        (SELECT id FROM genre WHERE name = 'Фантастика'),
        1967, 9.60);


--roles
--1 ADMIN
--2 USER
--
--users
--1 first@ff.by
--2 second@ff.by
--
--users_roles
--1 1
--1 2
--
--INSERT INTO users_roles (user_id, role_id) VALUES
--((SELECT id from roles where role_name = 'ADMIN'),
--(SELECT id from users where email = 'second@ff.by'))
