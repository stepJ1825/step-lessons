--21. Пронумеруйте все рейсы из Лондона (LHR) в хронологическом порядке
--за последний месяц.
--select f.flight_id,
	--   f.route_no,
--	 ROW_NUMBER() over (order by f.route_no, f.flight_id DESC) as row_number,
	-- ROW_NUMBER() over (partition by f.route_no
	 --					order by f.route_no, f.flight_id DESC) as row_number_partition
--from flights f
--limit 100
select  f.flight_id,
		f.scheduled_departure,
		r.departure_airport,
		ROW_NUMBER() over (order by f.scheduled_departure DESC)
from flights f
join routes r using (route_no)
where f.scheduled_departure >= CURRENT_DATE - interval '1 month'
	and f.scheduled_departure <= CURRENT_DATE
	and r.departure_airport = 'LHR'
order by f.route_no

--22. Для каждого маршрута (например, LHR → JFK) присвойте ранг
--каждому рейсу по стоимости самого дорогого билета.
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
limit 100

--23. Найдите разницу между максимальной и минимальной стоимостью билета
--на каждый рейс за последний месяц.
select f.flight_id,
		MAX(b.total_amount) max_amount,
		MIN(b.total_amount) min_amount,
		(MAX(b.total_amount) - MIN(b.total_amount)) as difference,
		count(b.total_amount) as "count"
from flights f
join segments s using (flight_id)
join tickets t using (ticket_no)
join bookings b using (book_ref)
where f.scheduled_departure >= CURRENT_DATE - interval '1 month'
	and f.scheduled_departure <= CURRENT_DATE
group by f.flight_id
having COUNT(*) >1
order by difference


--24. Рассчитайте скользящее среднее (7 дней) количества бронирований
--за последний квартал.
WITH daily_bookings as (select DATE(b.book_date) as day,
						count(*) as "cnt" from bookings b
where b.book_date >= CURRENT_DATE - interval '7 day'
		and b.book_date <= CURRENT_DATE
group by day
order by day)
select day,
		"cnt",
		AVG("cnt") over (order by day rows between 6 preceding
							and current row) as moving_avg_past,
		AVG("cnt") over (order by day rows between 3 preceding
							and 3 following) as moving_avg_middle
from daily_bookings

--25. Определите рейсы, где более 20% пассажиров сидят в первом ряду.
select
		f.flight_id,
		count(bp.seat_no) as total_count,
		count(bp.seat_no)
			filter (where SUBSTRING(bp.seat_no from '^\d+')::int = 1)
				as first_row_count,
		(count(bp.seat_no)
			filter (where SUBSTRING(bp.seat_no from '^\d+')::int = 1)::float
			* 100 / count(bp.seat_no))::NUMERIC(4,2) as ratio
from flights f
join boarding_passes bp using (flight_id)
group by f.flight_id
having count(bp.seat_no)
			filter (where SUBSTRING(bp.seat_no from '^\d+')::int = 1)::float
			* 100 / count(bp.seat_no) > 20
order by ratio;
