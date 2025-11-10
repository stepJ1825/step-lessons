--11. Найдите топ-5 аэропортов мира с наибольшим числом вылетов за последний месяц.
select r.departure_airport as rda, COUNT(*) as total_sum from flights f
join routes r on f.route_no = r.route_no
where f.actual_departure >= CURRENT_DATE - interval '1 month'
  and f.scheduled_departure <= CURRENT_DATE
group by rda
order by total_sum desc
limit 5;
-----------------------------------------------------------------------------------------
--12. Для каждой модели самолёта рассчитайте среднюю заполняемость рейсов
--за последний квартал.
--12.1 заполняемость пассажиров в штуках
select a.model,
      (sum(c1)/count(c1))::int as avg_count from
(select f.route_no as rn, f.scheduled_departure as sd, COUNT(*) as c1 from flights f
join segments s on s.flight_id = f.flight_id
where f.actual_departure >= CURRENT_DATE - interval '3 month'
  and f.scheduled_departure <= CURRENT_DATE
group by f.route_no, f.scheduled_departure)
join routes r on r.route_no = rn
join airplanes a on r.airplane_code = a.airplane_code
group by a.model
order by a.model;

--12.2 заполняемость самолётов в %:
SELECT
    model,
    AVG(COALESCE(sold, 0)::float / total)*100 AS avg_occupancy
FROM (
    -- Подзапрос: вместимость каждого рейса
    SELECT
        f.flight_id,
        a.model,
        COUNT(s.seat_no) AS total
    FROM flights f
    join routes r on r.route_no = f.route_no
    JOIN airplanes_data a ON r.airplane_code = a.airplane_code
    JOIN seats s ON a.airplane_code = s.airplane_code
    WHERE f.scheduled_departure >= CURRENT_DATE - INTERVAL '90 days'
    GROUP BY f.flight_id, a.model
) AS flight_capacity
LEFT JOIN (
    -- Подзапрос: проданные места по рейсам
    select f.flight_id, COUNT(*) AS sold
    FROM boarding_passes bp
    JOIN flights f ON bp.flight_id = f.flight_id
    WHERE f.scheduled_departure >= CURRENT_DATE - INTERVAL '90 days'
    GROUP BY f.flight_id
) AS sold ON flight_capacity.flight_id = sold.flight_id
GROUP BY model
ORDER BY avg_occupancy DESC;

--12.2 заполняемость самолётов в % через CTE (Common Table Expressions)
WITH flight_capacity AS (
    SELECT f.flight_id, a.model, COUNT(s.seat_no) AS total
    FROM flights f
    join routes r on r.route_no = f.route_no
    JOIN airplanes_data a ON r.airplane_code = a.airplane_code
    JOIN seats s ON a.airplane_code = s.airplane_code
    WHERE f.scheduled_departure >= CURRENT_DATE - INTERVAL '90 days'
    GROUP BY f.flight_id, a.model
),
sold AS (
    select f.flight_id, COUNT(*) AS sold
    FROM boarding_passes bp
    JOIN flights f ON bp.flight_id = f.flight_id
    WHERE f.scheduled_departure >= CURRENT_DATE - INTERVAL '90 days'
    GROUP BY f.flight_id
)
SELECT
    fc.model::JSON->>'ru' as "модель самолёта",
    (AVG(COALESCE(s.sold, 0)::float / fc.total)*100)::NUMERIC(4,2) || ' %'  AS "заполняемость в %"
FROM flight_capacity fc
LEFT JOIN sold s ON fc.flight_id = s.flight_id
GROUP BY fc.model
ORDER BY "заполняемость в %" DESC;

-----------------------------------------------------------------------------------------

--13. Выведите топ-10 самых дорогих бронирований за всё время.
select * from bookings b
order by b.total_amount
limit 10;
--13* самое дорогое бронирование
select * from bookings b
order by b.total_amount
limit 1;
--13** топ-10 самых дорогих бронирований c HAVING
select b.book_ref, b.book_date, b.total_amount
from bookings b
group by b.book_ref, b.total_amount
having b.total_amount >=
	(select b.total_amount from bookings b
	 order by b.total_amount DESC
	 offset 9 limit 1)
order by b.total_amount desc;


--14. Сколько билетов было продано ежедневно за последнюю неделю?
--14* выводить, если этих билетов больше 30000шт
select
DATE(b.book_date) as day_of_the_week,
count(t.ticket_no) as ticket_count
from bookings b
join tickets t on b.book_ref = t.book_ref
where b.book_date >= CURRENT_DATE - interval '1 week'
		and b.book_date <= CURRENT_DATE
group by day_of_the_week
--having count(t.ticket_no) > 30000	--14*
order by day_of_the_week;


--15. Найдите аэропорты, в которые прибыло менее 5 рейсов за последнюю неделю.
select  ad.airport_name::JSON->>'ru' as "name",
		city::JSON->>'ru' as "city",
		count(*)
from airports_data ad
join routes r on r.arrival_airport = ad.airport_code
join flights f on f.route_no = r.route_no
where f.actual_arrival  >= CURRENT_DATE - interval '1 week'
		and f.actual_arrival <= CURRENT_DATE
group by "name", city
having count(*)<10