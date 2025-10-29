--6. Для каждого рейса выведите: номер рейса, аэропорт вылета (название),
--аэропорт прилёта (название), дату и время вылета.
--(вместо кода вывести название аэропорта)
SELECT  f.flight_id as "номер рейса",
        add.airport_name  as "аэропорт вылета",
        ada.airport_name  as "аэропорт прилёта",
        f.scheduled_departure::date as "дата вылета",
        f.scheduled_departure::time as "время вылета"
FROM flights f
JOIN routes r on r.route_no = f.route_no
JOIN airports_data add ON r.departure_airport = add.airport_code
JOIN airports_data ada on r.arrival_airport = ada.airport_code

--7. Выведите список пассажиров (ФИО) и их номера билетов для рейса
--LH400 (Люфтганза, Франкфурт → Нью-Йорк), вылетевшего вчера.
SELECT t.passenger_name, f.route_no from tickets t
JOIN segments s on s.ticket_no = t.ticket_no
JOIN flights f on s.flight_id = f.flight_id
WHERE --f.route_no = 'LH400' AND --в малой базе таких перелётов нету
  f.actual_arrival >= CURRENT_DATE - interval '1 day'
AND f.actual_arrival <= CURRENT_DATE;

--8. Найдите все рейсы, выполняемые на самолёте Boeing 777-300ER,
--за последний месяц.
SELECT f.route_no,
       r.departure_airport AS "откуда",
       r.arrival_airport AS "куда",
       r.scheduled_time
FROM flights f
JOIN routes r on r.route_no = f.route_no
JOIN airplanes_data ad ON r.airplane_code = ad.airplane_code
WHERE
ad.model = '{"en": "Boeing 777-300ER", "ru": "Боинг 777-300ER"}' --TODO: работа с текстом
and  f.actual_arrival >= CURRENT_DATE - interval '1 month'
AND f.actual_arrival <= CURRENT_DATE;

--9. Для каждого аэропорта мира рассчитайте среднее число ежедневных
--вылетов за последний квартал.
SELECT ad.airport_name, COUNT(*)::float/90 as "average flights" --TODO: округление дробных
from airports_data ad
JOIN routes r ON r.departure_airport = ad.airport_code
JOIN flights f ON f.route_no = r.route_no
WHERE f.actual_arrival >= CURRENT_DATE - interval '3 month'
     AND f.actual_arrival <= CURRENT_DATE
GROUP BY (ad.airport_name);


--10. Выведите информацию о бронировании: номер брони, дата, общая стоимость
--и список всех билетов в этой брони (номер + пассажир).
SELECT b.book_ref as "номер брони",
		DATE(b.book_date) as "дата" ,
		b.total_amount as "общая стоимость",
		STRING_AGG(t.ticket_no || ' ' || t.passenger_name , ', ') as "пассажиры"
from bookings b
JOIN tickets t on t.book_ref = b.book_ref
group by b.book_ref
