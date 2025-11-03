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

--12.2 заполняемость самолётов в % --TODO:
SELECT
    model,
    AVG(COALESCE(sold, 0)::float / total) AS avg_occupancy
FROM (
    -- Подзапрос: вместимость каждого рейса
    SELECT
        f.flight_id,
        a.model,
        COUNT(s.seat_no) AS total
    FROM flights f
    JOIN aircrafts a ON f.aircraft_code = a.aircraft_code
    JOIN seats s ON f.aircraft_code = s.aircraft_code
    WHERE f.scheduled_departure >= CURRENT_DATE - INTERVAL '90 days'
    GROUP BY f.flight_id, a.model
) AS flight_capacity
LEFT JOIN (
    -- Подзапрос: проданные места по рейсам
    SELECT
        f.flight_id,
        COUNT(*) AS sold
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
    JOIN aircrafts a ON f.aircraft_code = a.aircraft_code
    JOIN seats s ON f.aircraft_code = s.aircraft_code
    WHERE f.scheduled_departure >= CURRENT_DATE - INTERVAL '90 days'
    GROUP BY f.flight_id, a.model
),
sold AS (
    SELECT flight_id, COUNT(*) AS sold
    FROM boarding_passes bp
    JOIN flights f ON bp.flight_id = f.flight_id
    WHERE f.scheduled_departure >= CURRENT_DATE - INTERVAL '90 days'
    GROUP BY flight_id
)
SELECT
    fc.model,
    AVG(COALESCE(s.sold, 0)::float / fc.total) AS avg_occupancy
FROM flight_capacity fc
LEFT JOIN sold s ON fc.flight_id = s.flight_id
GROUP BY fc.model
ORDER BY avg_occupancy DESC;

-----------------------------------------------------------------------------------------

--13. Выведите топ-10 самых дорогих бронирований за всё время.
--14. Сколько билетов было продано ежедневно за последнюю неделю?
--15. Найдите аэропорты, в которые прибыло менее 5 рейсов за последнюю неделю.