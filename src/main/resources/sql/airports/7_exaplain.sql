explain
select * from airplanes_data ad
where ad.airplane_code = '789'
--where ad.model::json->>'en' like '%Aerobus%'

explain (ANALYZE)
select t.passenger_name, count(f.flight_id) from tickets t
join boarding_passes bp using (ticket_no)
join flights f using (flight_id)
where f.scheduled_departure >= CURRENT_DATE - interval '3 year'
	and f.scheduled_departure <= CURRENT_DATE
group by t.passenger_name
having count(f.flight_id) > 5
order by count(f.flight_id) DESC