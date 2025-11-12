--26. Какой день недели самый загруженный по числу вылетов в Европе?
--(Определите Европу по списку кодов аэропортов или стран —
--для упрощения можно использовать известные коды:
--LHR, CDG, FRA, AMS и т.д.)
select EXTRACT(ISODOW FROM f.scheduled_departure) as day_of_week,
		count(*) as flights_count
from flights f
join routes r using (route_no)
join airports_data ad on r.departure_airport = ad.airport_code
where ad.timezone like 'Europe%'
group by day_of_week
order by flights_count DESC
limit 1
--https://postgrespro.ru/docs/postgresql/17/functions-datetime#FUNCTIONS-DATETIME-EXTRACT


--27. Найдите «недоиспользованные» рейсы — где продано
--менее 25% мест за последнюю неделю.
with
capacity as (select f.flight_id, count(s.seat_no) as capacity_count
					from flights f
					join routes r using (route_no)
					join airplanes_data ad using (airplane_code)
					join seats s using (airplane_code)
					where f.scheduled_departure >= CURRENT_DATE - interval '1 week'
					and f.scheduled_departure <= CURRENT_DATE
					group by f.flight_id),
solded as (select bp.flight_id, count(*) as solded_count
				from boarding_passes bp
				group by bp.flight_id)
select c.flight_id,
		c.capacity_count,
		s.solded_count,
		s.solded_count::float/c.capacity_count as ratio
from capacity c
join solded s on c.flight_id = s.flight_id
where s.solded_count::float/c.capacity_count < 0.25


--28. Есть ли рейсы, у которых фактическое время прилёта раньше вылета
--(с учётом часовых поясов)?  *(Примечание: в БД нет часовых поясов,
--но можно проверить по UTC-времени.)*
select f.flight_id ,
		f.actual_departure,
		f.actual_arrival
		from flights f
where f.actual_departure::time > f.actual_arrival::time
  --and f.actual_departure::date = f.actual_arrival::date


--29. Выведите пассажиров, которые летали более 5 раз за последний год.
select t.passenger_name, count(f.flight_id) from tickets t
join boarding_passes bp using (ticket_no)
join flights f using (flight_id)
where f.scheduled_departure >= CURRENT_DATE - interval '1 year'
		and f.scheduled_departure <= CURRENT_DATE
group by t.passenger_name
having count(f.flight_id) > 5
order by count(f.flight_id) DESC


--30. Предложите 3 индекса, которые значительно ускорят аналитические запросы по маршрутам и доходам.