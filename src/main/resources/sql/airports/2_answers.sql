--6. Для каждого рейса выведите: номер рейса, аэропорт вылета (название),
--аэропорт прилёта (название), дату вылета.
-- TODO: вместо кода вывести название аэропорта
SELECT flight_no as "номер рейса",
        r.departure_airport as "аэропорт вылета",
        r.arrival_airport as "аэропорт прилёта",
        scheduled_departure as "дату вылета"
    FROM flights f
JOIN routes r USING route_no     --on r.route_no = f.route_no
JOIN airports_data ad ON r.departure_airport = ad.airport_code
                      OR r.arrival_airport = ad.airport_code

--7. Выведите список пассажиров (ФИО из tickets.passenger_name) и
--их номера билетов для рейса SU9 от 2016-09-15.
SELECT t.passenger_name from tickets t
JOIN segments s USING ticket_no
JOIN flights f USING flight_id
WHERE f.actual_arrival = '2016-09-15' --TODO:проверить преобразование в дату
   AND f.route_no = 'SU9'

--8. Найдите все рейсы, выполняемые на самолёте модели «Boeing 777-300» (или «Боинг 777-300»),
-- с указанием даты и маршрута.
SELECT "Boeing 777-300",
       f.*,
       r.departure_airport AS "откуда",
       r.arrival_airport AS "куда",
       r.scheduled_time
FROM flights f
JOIN routes r USING route_no
JOIN airplanes_data ad ON r.airplane_code = ad.airplane_code
WHERE ad.model IN('Boeing 777-300','Боинг 777-300')

--9. Для каждого аэропорта укажите, сколько рейсов из него вылетает ежедневно
--(в среднем за сентябрь 2016).
SELECT ad.airport_name, COUNT(f.flight_id) from airports_data ad
JOIN routes r ON r.departure_airport = ad.airport_code
JOIN flights f ON f.route_no = r.route_no
WHERE f.actual_arrival BETWEEN '01-09-2016' AND '30-09-2016' --TODO:
GROUP BY (ad.airport_name)  -- TODO: WHERE vs HAVING

--10. Выведите информацию о бронировании: номер брони, дата, общая стоимость,
--и список всех билетов в этой брони (номера+пассажиры).
SELECT b.*, t.tickets_no, t.passenger_name from bookings b
JOIN tickets t USING book_ref -- TODO:
--1  2025-10-27  5000$  билет1 Иванов
--1  2025-10-27  5000$  билет2 Петров
--1  2025-10-27  5000$  билет3 Сидоров

