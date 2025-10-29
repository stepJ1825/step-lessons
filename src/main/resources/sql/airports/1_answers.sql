--1. Выведите список всех аэропортов мира с кодом IATA, названием и городом.
--Отсортируйте по названию города.
SELECT airport_code, airport_name, city FROM airports_data
ORDER BY city;

--2. Найдите все рейсы, вылетающие из аэропорта Шереметьево (SVO) .
--за последний месяц.
SELECT f.*, ad.airport_code FROM flights f
JOIN routes r on r.route_no = f.route_no
JOIN airports_data ad on r.departure_airport = ad.airport_code
WHERE airport_code = 'SVO'
AND f.scheduled_departure >= CURRENT_DATE - interval '1 month'
and f.scheduled_departure <= CURRENT_DATE;
	--LocalDateTime 	  >= LocalDateTime - Period

--3. Выведите модели всех самолётов, упорядоченные по дальности полёта
--(от большей к меньшей).
SELECT model FROM airplanes_data
ORDER BY range DESC;

--4. Сколько всего бронирований было совершено за последнюю неделю?
SELECT count(*) FROM bookings b
WHERE b.book_date >= CURRENT_DATE - interval '1 week'
AND b.book_date <= CURRENT_DATE;

--5. Найдите все рейсы, прибывающие в аэропорт
--Джона Кеннеди (JFK, Нью-Йорк) за последние 3 дня.
SELECT f.flight_id, scheduled_arrival::time AS "Время прилёта" FROM flights f
JOIN routes r on r.route_no = f.route_no
WHERE r.arrival_airport  = 'JFK'
AND  f.scheduled_arrival >= CURRENT_DATE - interval '3 day'
AND f.scheduled_arrival <= CURRENT_DATE;