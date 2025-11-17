--29.   1.8..2.0 sec
explain (ANALYZE)
select t.passenger_name, count(f.flight_id) from tickets t
join boarding_passes bp using (ticket_no)
join flights f using (flight_id)
where f.scheduled_departure >= CURRENT_DATE - interval '3 year'
	and f.scheduled_departure <= CURRENT_DATE
group by t.passenger_name
having count(f.flight_id) > 5
order by count(f.flight_id) desc





explain (ANALYZE)
select * from flights f
where f.scheduled_departure >= CURRENT_DATE - interval '1 month'
	and f.scheduled_departure <= CURRENT_DATE
union all
select * from flights f
where f.scheduled_departure >= CURRENT_DATE - interval '32 day'
	and f.scheduled_departure <= CURRENT_DATE - interval '1 month'


explain (ANALYZE)
select f.scheduled_departure from flights f
where f.scheduled_departure >= CURRENT_DATE - interval '1 month'
	and f.scheduled_departure <= CURRENT_DATE

explain (ANALYZE)
select f.* from flights f
where f.route_no in
(select r.route_no from routes r where r.airplane_code = '339')

--22. Для каждого маршрута (например, LHR → JFK) присвойте ранг
--каждому рейсу по стоимости самого дорогого билета.
-- 486ms без доп индекса
explain (ANALYZE)
select  b.book_ref,
		r.route_no,
		b.total_amount,
		r.departure_airport, r.arrival_airport,
		RANK() over (PARTITION by r.departure_airport,
									 r.arrival_airport
					 order by b.total_amount desc) as "rank"
from bookings b
join tickets t using (book_ref)
join segments s using (ticket_no)
join flights f using (flight_id)
join routes r using (route_no)
where r.departure_airport = 'LHR'
		and r.arrival_airport = 'ORY'
order by "rank"

create index routes_dep_arr_aitports
on routes (departure_airport, arrival_airport)