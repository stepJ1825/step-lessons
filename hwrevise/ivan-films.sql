CREATE TABLE persons
(
    id         SERIAL PRIMARY KEY,
    fullname   VARCHAR,
    birthday   DATE
);

CREATE TABLE films
(
    id           SERIAL PRIMARY KEY,
    title        VARCHAR,
    release_date DATE,
    country      VARCHAR(3),
    --director_ID  INTEGER  REFERENCES persons (id)
);

CREATE TABLE persons_films
(
    person_id         INTEGER  REFERENCES persons (id),
    films_id          INTEGER  REFERENCES films (id),
    responsibility    VARCHAR
);

insert into persons_films (person_id, films_id, responsibility)
VALUES (1, 1, 'Director'),
        (2, 1, 'Actor'),
        (3, 1, 'Actor'),
        (4, 1, 'Actor'),
        (5, 1, 'Actor');

--Вывести информацию об актерах, которые были режиссерами хотя бы одного из фильмов.
SELECT * FROM persons p
where p.id IN (SELECT person_id FROM
                   (SELECT person_id, COUNT(responsibility) FROM persons_films
                   GROUP BY person_id
                   HAVING COUNT(responsibility) = 2))



