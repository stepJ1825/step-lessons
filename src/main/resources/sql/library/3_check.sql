-- Проверка: вывод всех книг с авторами и жанрами
SELECT
    b.title AS "Название",
    a.first_name || ' ' || a.surname AS "Автор",
    g.name AS "Жанр",
    b.year AS "Год",
    b.rating AS "Рейтинг"
FROM book b
JOIN author a ON b.author_id = a.id
JOIN genre g ON b.genre_id = g.id
ORDER BY b.rating DESC;

-- union all example
(select b.title, b.year from book b
JOIN author a ON b.author_id = a.id
where a.surname = 'Толстой'
limit 1)
union all
(select b.title, b.year from book b
JOIN genre g ON b.genre_id = g.id
where g.name = 'Фантастика'
limit 1)

-- subselect example
select b.title, b.year from book b
where id in
(select b.id from book b
JOIN author a ON b.author_id = a.id
where a.surname = 'Толстой')

-- like example
select b.title, a.surname from book b
JOIN author a ON b.author_id = a.id
where a.surname like '%ст%'

-- between example
select b.* from book b
where year between 1900 and 2000