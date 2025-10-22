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