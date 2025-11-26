-- 1. Создание таблиц
CREATE TABLE Passenger
(
    id   SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE Trip
(
    id        INTEGER PRIMARY KEY,
    company   INTEGER,
    plane     TEXT,
    town_from TEXT,
    town_to   TEXT,
    time_out  TIMESTAMP,
    time_in   TIMESTAMP
);

CREATE TABLE Passenger_in_trip
(
    id        SERIAL PRIMARY KEY,
    trip      INTEGER REFERENCES Trip (id),
    passenger INTEGER REFERENCES Passenger (id),
    place     TEXT
);

-- 2. Наполнение таблицы Passenger (включая тёзок)
INSERT INTO Passenger (id, name)
VALUES (1, 'Bruce Willis'),
       (2, 'George Clooney'),
       (3, 'Kevin Costner'),
       (4, 'Donald Sutherland'),
       (5, 'George Clooney'),     -- тёзка №2
       (6, 'Tom Hanks'),
       (7, 'Tom Hanks'),          -- тёзка №2
       (8, 'Brad Pitt'),
       (9, 'Leonardo DiCaprio'),
       (10, 'Leonardo DiCaprio'), -- тёзка №2
       (11, 'Julia Roberts');
-- без тёзки

-- 3. Наполнение Trip (опционально, для полноты)
INSERT INTO Trip (id, company, plane, town_from, town_to, time_out, time_in)
VALUES (1100, 4, 'Boeing', 'Rostov', 'Paris', '1900-01-01 14:30:00', '1900-01-01 17:50:00'),
       (1101, 4, 'Boeing', 'Paris', 'Rostov', '1900-01-01 08:12:00', '1900-01-01 11:45:00'),
       (1123, 3, 'TU-154', 'Rostov', 'Vladivostok', '1900-01-01 16:20:00', '1900-01-02 03:40:00');

-- 4. Наполнение Passenger_in_trip (опционально)
INSERT INTO Passenger_in_trip (trip, passenger, place)
VALUES (1100, 1, '1a'),
       (1123, 3, '2a'),
       (1123, 5, '4c'),
       (1100, 6, '3b'),
       (1101, 10, '5d');