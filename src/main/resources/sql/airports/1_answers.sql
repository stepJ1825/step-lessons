--1.Выведите список всех аэропортов с их кодами (IATA), названиями и городами.
--Отсортируйте по названию города.
SELECT airport_code, airport_name, city FROM airports_data
ORDER BY city;

--2.Найдите все рейсы, вылетающие из аэропорта «Домодедово» (код DME) в сентябре 2016 года.
SELECT f.* FROM flights f
JOIN routes r USING route_no     --on r.route_no = f.route_no
JOIN airports_data ad on r.departure_airport = ad.airport_code
WHERE airport_code = 'DME'
AND f.scheduled_departure BETWEEN --TODO: преобразовать в нужный тип данных и сравнить

--3.Выведите модели всех самолётов, упорядоченные по дальности полёта (от большей к меньшей).
SELECT model FROM airplanes_data
ORDER BY range DESC;

--4.Сколько всего бронирований было совершено в октябре 2016 года?
SELECT COUNT(*) FROM bookings
WHERE book_date BETWEEN '01-10-2016' AND '31-10-2016' -- TODO: проверить

--5.Найдите все рейсы, прибывающие в аэропорт «Пулково» (код LED), с указанием времени прилёта.
SELECT f.*, scheduled_arrival AS "Время прилёта" FROM flights f --TODO: преобразовать TIMESTAMP во время.
JOIN routes r USING route_no     --on r.route_no = f.route_no
JOIN airports_data ad on r.arrival_airport = ad.airport_code
WHERE airport_code = 'LED'